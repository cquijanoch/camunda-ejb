app.controller("TesoreriaController",function($scope,UserService,modal,NgTableParams){
	$scope.efectivo = {monto:0,isCheck:false};
	$scope.cheque = {monto:0,isCheck:false};
	$scope.cuenta = {monto:0,isCheck:false};
	var params = {count: 10};
    var setting = {counts: []};
    $scope.miTabla = new NgTableParams(params, setting);
    
	$scope.iniciarDatos=function()
	{

		UserService.getAll("resources/expedientesDiga").then(
				function(response){
					setting.dataset = response;
		            iniciarPosiciones(setting.dataset);
		            $scope.miTabla.settings(setting);
				},
				function(error){
					 console.info(error);
				}
		);
	

	};
	$scope.prepararEnviarPago = function(o) {
		$scope.efectivo = {monto:0,isCheck:false};
		$scope.cheque = {monto:0,isCheck:false};
		$scope.cuenta = {monto:0,isCheck:false};
		$("#modalPago").modal('show');
	};
	$scope.enviarPago = function() {
		if (!$scope.efectivo.isCheck && !$scope.cheque.isCheck	&& !$scope.cuenta.isCheck) {
			modal.mensaje("VERIFICACION", "Seleccione un Modo de Pago");
		} else if ($scope.efectivo.isCheck && !$scope.cheque.isCheck && !$scope.cuenta.isCheck) {
			modal.mensaje("CONFIRMACION", "Pago en Efectivo realizado");
			$("#modalPago").modal('hide');
		}else
			{
//				$("#modalNuevo").modal('show');
			}
		
	};
	
//	$scope.enviarPago = function() {
//		$("#modalNuevo").modal('hide');
//	};
	
});
	