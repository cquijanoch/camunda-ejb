	
app.controller("TesoreriaController",function($scope,UserService,modal,NgTableParams){
	
	var params = {count: 10};
    var setting = {counts: []};
    $scope.tabla1 = new NgTableParams(params, setting);
    
	$scope.listar1=function()
	{

		UserService.getAll("resources/tesoreria").then(
				function(response){
					
		            var process = response.body.task !== null ? response.body.task:[];
		            setting.dataset = [];
		            for (var i = 0; i < process.length; i++) setting.dataset.push(process[i].headerDto);
		            iniciarPosiciones(setting.dataset);
		            $scope.tabla1.settings(setting);
		            console.log(response);
		            console.log('Exito3333');
		            
		            
				},
				function(error){
					console.log('Error');
				}
		);
	

	};
	
	$scope.seleccionarModoPago = function(o) {
		$scope.reqActual = o;
		$("#modalPago").modal('show');
	};
	
	$scope.guardarModoPago = function(m){
		m;
		$scope.request = {
	        header: {
	            executionId: $scope.reqActual.executionId,
	            taskId: $scope.reqActual.taskId
	        },
	        body: m
	    };

	    UserService.add("resources/tesoreria", $scope.request).then(
	        function(response) {

	            modal.mensaje("CONFIRMACION", "SE GUARDO EL PAGO");
	            eliminarElemento(setting.dataset, $scope.reqActual['i']);
	            $scope.tabla1.reload();
	            
	        },
	        function(error) {
	            alert('Error');
	        });
	};	
	
//	$scope.enviarPago = function() {
//		if (!$scope.efectivo.isCheck && !$scope.cheque.isCheck	&& !$scope.cuenta.isCheck) {
//			modal.mensaje("VERIFICACION", "Seleccione un Modo de Pago");
//		} else if ($scope.efectivo.isCheck && !$scope.cheque.isCheck && !$scope.cuenta.isCheck) {
//			modal.mensaje("CONFIRMACION", "Pago en Efectivo realizado");
//			$("#modalPago").modal('hide');
//		}else
//			{
////				$("#modalNuevo").modal('show');
//			}
//		
//	};
	
	//////// GIRAR CHEQUE
	
	var params2 = {count: 10};
    var setting2 = {counts: []};
    $scope.tabla2 = new NgTableParams(params2, setting2);
    
	$scope.listar2=function()
	{

		UserService.getAll("resources/tesoreria/pagoscheques").then(
				function(response){
					
		            var process = response.body.task !== null ? response.body.task:[];
		            setting2.dataset = [];
		            for (var i = 0; i < process.length; i++) setting2.dataset.push(process[i].headerDto);
		            iniciarPosiciones(setting2.dataset);
		            $scope.tabla2.settings(setting2);
		            console.log(response);
		            console.log('Exito3333');
		            
		            
				},
				function(error){
					console.log('Error');
				}
		);

	};
	
	$scope.girarCheque = function(o) {
		$scope.reqActual = o;
		$("#modalCheque").modal('show');
	};
		
	$scope.guardarPagoCheque = function(){
		
		$scope.request = {
	        header: {
	            executionId: $scope.reqActual.executionId,
	            taskId: $scope.reqActual.taskId
	        },
	        body: {}
	    };

	    UserService.add("resources/tesoreria", $scope.request).then(
	        function(response) {

	            modal.mensaje("CONFIRMACION", "SE GUARDO EL PAGO");
	            eliminarElemento(setting2.dataset, $scope.reqActual['i']);
	            $scope.tabla2.reload();
	            
	        },
	        function(error) {
	            alert('Error');
	        });
	};
	
	
	//////// DEPOSITAR CUENTA BANCARIA
	
	var params3 = {count: 10};
    var setting3 = {counts: []};
    $scope.tabla3 = new NgTableParams(params3, setting3);
    
	$scope.listar3=function()
	{

		UserService.getAll("resources/tesoreria/depositoCuenta").then(
				function(response){
					
		            var process = response.body.task !== null ? response.body.task:[];
		            setting3.dataset = [];
		            for (var i = 0; i < process.length; i++) setting3.dataset.push(process[i].headerDto);
		            iniciarPosiciones(setting3.dataset);
		            $scope.tabla3.settings(setting3);
		            console.log(response);
		            console.log('Exito3333');
		            
				},
				function(error){
					console.log('Error');
				}
		);
	

	};
	
	$scope.depositarCuenta = function(o) {
		$scope.reqActual = o;
		$("#modalCuentaBancaria").modal('show');
	};	
	
	$scope.guardarPagoCuenta = function(m){
		m;
		$scope.request = {
	        header: {
	            executionId: $scope.reqActual.executionId,
	            taskId: $scope.reqActual.taskId
	        },
	        body: {}
	    };

	    UserService.add("resources/tesoreria", $scope.request).then(
	        function(response) {

	            modal.mensaje("CONFIRMACION", "SE GUARDO EL PAGO");
	            eliminarElemento(setting3.dataset, $scope.reqActual['i']);
	            $scope.tabla3.reload();
	            
	        },
	        function(error) {
	            alert('Error');
	        });
	};
	

});
	
