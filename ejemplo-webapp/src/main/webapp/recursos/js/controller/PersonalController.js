app.controller("PersonalController",function($scope,UserService,modal,NgTableParams){
	
	var paramsTabla1 = {
		    count: 10
		};
		var settingTabla1 = {
		    counts: []
		};
		$scope.tabla1 = new NgTableParams(paramsTabla1, settingTabla1);


		$scope.listar1 = function() {

		    //        UserService.getAll("resources/aprobarRequisito").then(
		    //				function(response){
		    //
		    //					console.log(response);
		    //					console.log("11111111-------1111111");
		    //				},
		    //				function(error){
		    //					alert('Error');
		    //				}
		    //		);
		    UserService.getTasks("resources/mesapartes", null).then(
		        function(response) {
		            //					setting.dataset = response;
		            //		            iniciarPosiciones(setting.dataset);
		            //		            $scope.miTabla.settings(setting);

		            var process = response.body.task;
		            settingTabla1.dataset = [];
		            
		            
		            
		            for (var i = 0; i < process.length; i++){
		            	var header = process[i].headerDto;
		            	var requerimiento = process[i].body;
		            	var data= {
				            	'processInstanceId': header.processInstanceId,
				            	'executionId':header.executionId,
				            	'taskId':header.taskId,
				            	'perDNI':requerimiento.usuarioDto.dni,
				            	'perNom':requerimiento.usuarioDto.nombre,
				            	'perAsu':requerimiento.asunto
				            }
		            	
		            	settingTabla1.dataset.push(data);
		            }
		            
		            iniciarPosiciones(settingTabla1.dataset);
		            $scope.tabla1.settings(settingTabla1);
		            console.log(response);
		            console.log('Exito3333')
		        },
		        function(error) {
		            console.log('Error');
		        }
		    )



		};

		//$scope.listar1();

		var paramsTabla2 = {
		    count: 10
		};
		var settingTabla2 = {
		    counts: []
		};

		var getSuccessTaskCallback = function(response) {
		    settingTabla2.dataset = [];

		    var tasks = response.body.task;
		    
		    if(tasks != undefined){

			    for (i = 0; i < tasks.length; i++) {
	
			        var body = tasks[i].body;
			        var header = tasks[i].headerDto;
			        var data = {
			            'perDNI': '',
			            'perNom': '',
			            'perAsu': '',
			            'executionId': header.executionId,
			            'taskId': header.taskId
			        }
	
			        settingTabla2.dataset.push(data);
			    }
		    }

		    iniciarPosiciones(settingTabla2.dataset);
		    $scope.tabla2.settings(settingTabla2);
		    console.log(response);
		};

		var getErrorTaskCallback = function(error) {
		    alert('Error');
		};

		$scope.tabla2 = new NgTableParams(paramsTabla2, settingTabla2);

		$scope.listar2 = function() {
		    UserService.getAll("resources/requerimientoMesaPartes/getAll").then(getSuccessTaskCallback, getErrorTaskCallback);
		};


//		$scope.listar2();

		$scope.reqActual = {};

		$scope.aprobarRequerimiento = function(i, d) {
		    $scope.reqActual = d;
		    $("#modalAprobar").modal('show');

		};

		$scope.aprobarReq = function(f) {

		    //    	$scope.reqActual.estado = f;    
		    //    	delete $scope.reqActual['i'];  

		    $scope.request = {
		        header: {
		            executionId: $scope.reqActual.executionId,
		            taskId: $scope.reqActual.taskId
		        },
		        body: {
		            completed: f
		        }
		    };

		    UserService.add("resources/mesapartes", $scope.request).then(
		        function(response) {
		            // modal.mensajeConfirmacion($scope,"SI INGRESO
		            // CORRECTAMENTE",function(){},400);
		            modal.mensaje("CONFIRMACION", "SE APROBO CORRECTAMENTE");
		            eliminarElemento(settingTabla1.dataset, $scope.reqActual['i']);
		            $scope.tabla1.reload();
		        },
		        function(error) {
		            alert('Error');
		        });



		    //    	$scope.reqActual.estado = f;    
		    //    	delete $scope.reqActual['i'];
		    //    	UserService.add("resources/aprobarRequisito", $scope.reqActual).then(
		    //				function(response){
		    //					console.log("se aprobo el requerimiento");
		    //				},
		    //				function(error){
		    //					alert('Error');
		    //				}
		    //		);

		};



		$scope.registrarRequerimiento = function(i, d) {
		    $scope.reqActual = d;
		    $("#modalRegistrarReq").modal('show');
		};

		$scope.registrarReq = function(d) {
		    $scope.reqActual.detalle = d;
		    
		    var request = {
			        header: {
			            executionId: $scope.reqActual.executionId,
			            taskId: $scope.reqActual.taskId
			        },
			        body: {
			            'requerimientoId': '',
			            'asunto':'',
			            'detalle': $scope.reqActual.detalle
			        }
			    };
		    
		    UserService.add("resources/requerimientoMesaPartes/save", request).then(
			        function(response) {
			            modal.mensaje("CONFIRMACION", "SE APROBO CORRECTAMENTE");
			            eliminarElemento(settingTabla2.dataset, $scope.reqActual['i']);
			            $scope.tabla2.reload();
			        },
			        function(error) {
			            alert('Error');
			        });
		};

//		    $scope.cerrarModalPariente = function(){
//		        $scope.listar();
//		        $('#modalEditar').modal('show');                
//		    }
    
    
});
	