app.controller("PersonalController",function($scope,UserService,modal,NgTableParams){
    
    var paramsTabla1 = {count: 10};
    var settingTabla1 = {counts: []};
    $scope.tabla1 = new NgTableParams(paramsTabla1, settingTabla1);
  
        
    $scope.listar1 = function(){
        
        UserService.getAll("resources/mesapartes").then(
				function(response){
//					settingTabla1.dataset = response;
					
					 var process = response.body.task;
			         settingTabla1.dataset = [];
					
					for (var i = 0; i < process.length; i++){
		            	var header = process[i].headerDto;
		            	var requerimiento = process[i].body;
		            	var data= {
				            	'processInstanceId': header.processInstanceId,
				            	'executionId':header.executionId,
				            	'taskId':header.taskId,
				            	'dni':requerimiento.dni,
				            	'nombre':requerimiento.nombre,
				            	'asunto':requerimiento.asunto,
				            	'estado':requerimiento.estado,
				            	'requerimientoId':requerimiento.requerimientoId,
				            	'usuarioId':requerimiento.usuarioId,
				            	'id':requerimiento.id
				            }
		            	
		            	settingTabla1.dataset.push(data);
		            }
					
		            iniciarPosiciones(settingTabla1.dataset);
		            $scope.tabla1.settings(settingTabla1);
				},
				function(error){
					alert('Error');
				}
		);       
    };
   
    
    var paramsTabla2 = {count: 10}; 
    var settingTabla2 = {counts: []};
    $scope.tabla2 = new NgTableParams(paramsTabla2, settingTabla2);
        
    $scope.listar2 = function(){
        
        UserService.getAll("resources/mesapartes/bandejaAprobados").then(
				function(response){
//					settingTabla2.dataset = response;
					settingTabla2.dataset = [];

				    var tasks = response.body.task;
				    
				    if(tasks != undefined){

					    for (i = 0; i < tasks.length; i++) {
			
					        var body = tasks[i].body;
					        var header = tasks[i].headerDto;
					        var data = {
					            'dni': body.dni,
					            'nombre': body.nombre,
					            'asunto': body.asunto,
					            'requerimientoId':body.requerimientoId,
					            'usuarioId':body.usuarioId,
					            'id':body.id,
					            'estado':body.estado,
					            'executionId': header.executionId,
					            'taskId': header.taskId
					        }
			
					        settingTabla2.dataset.push(data);
					    }
				    }
		            iniciarPosiciones(settingTabla2.dataset);
		            $scope.tabla2.settings(settingTabla2);
				},
				function(error){
					alert('Error');
				}
		);
        
        
                
    };
    
            
    $scope.reqActual = {};
    
    $scope.aprobarRequerimiento = function(i, d){    	
    	$scope.reqActual = d;
    	$("#modalAprobar").modal('show');
    	
    };
    
    $scope.aprobarReq = function(f){
    	
    	$scope.reqActual.estado = f;    
    	delete $scope.reqActual['i'];
    	
    	$scope.request = {
		        header: {
		            executionId: $scope.reqActual.executionId,
		            taskId: $scope.reqActual.taskId
		        },
		        body: {
		            	'dni':$scope.reqActual.dni,
		            	'nombre':$scope.reqActual.nombre,
		            	'asunto':$scope.reqActual.asunto,
		            	'estado':$scope.reqActual.estado,
		            	'requerimientoId':$scope.reqActual.requerimientoId,
		            	'usuarioId':$scope.reqActual.usuarioId,
		            	'id':$scope.reqActual.id,
		            }
		};
    	
    	
//    	if(f=='A')
//    	{
//    		UserService.update("resources/mesaPartes/aprobarRequisito", $scope.request).then(
//    				function(response){
//    					modal.mensaje("CONFIRMACION","Se Aprobo Correctamente");
//    					$scope.listar1();
//    					$scope.listar2(); 
//    				},
//    				function(error){
//    					alert('Error');
//    				}
//    		);
//    	}
//    	
//    	if(f=='D')
//    	{
//    		UserService.update("resources/mesaPartes/desaprobarRequisito", $scope.request).then(
//    				function(response){
//    					modal.mensaje("CONFIRMACION","Se Desaprobo Correctamente");
//    					$scope.listar1();
//    					$scope.listar2(); 
//    				},
//    				function(error){
//    					alert('Error');
//    				}
//    		);
//    	}
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
    	    	
    };
    
    
    
    $scope.registrarRequerimiento = function(i, d){
    	$scope.reqActual = d;
    	$("#modalRegistrarReq").modal('show');    	
    };
    
//    $scope.registrarReq = function(d){
//    	$scope.reqActual.detalle = d;   
//    	delete $scope.reqActual['i'];
//    	UserService.update("resources/mesaPartes/registrarRequerimiento", $scope.reqActual).then(
//				function(response){
//					modal.mensaje("CONFIRMACION","Se Registro Correctamente");
//					$scope.listar2(); 
//					$scope.reqActual = {};
//					
//				},
//				function(error){
//					alert('Error');
//				}
//		);
//    };
    $scope.registrarReq = function(d) {
	    $scope.reqActual.detalle = d;
	    
	    var request = {
		        header: {
		            executionId: $scope.reqActual.executionId,
		            taskId: $scope.reqActual.taskId
		        },
		        body: {
		            	'dni':$scope.reqActual.dni,
		            	'nombre':$scope.reqActual.nombre,
		            	'asunto':$scope.reqActual.asunto,
		            	'estado':$scope.reqActual.estado,
		            	'requerimientoId':$scope.reqActual.requerimientoId,
		            	'usuarioId':$scope.reqActual.usuarioId,
		            	'id':$scope.reqActual.id,
		            	'detalle':$scope.reqActual.detalle
		            }
		};
	    
	    UserService.add("resources/mesapartes/save", request).then(
		        function(response) {
		            modal.mensaje("CONFIRMACION", "SE APROBO CORRECTAMENTE");
		            eliminarElemento(settingTabla2.dataset, $scope.reqActual['i']);
		            $scope.tabla2.reload();
		        },
		        function(error) {
		            alert('Error');
		        });
	};

//    $scope.cerrarModalPariente = function(){
//        $scope.listar();
//        $('#modalEditar').modal('show');                
//    }
	
});
	