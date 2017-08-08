app.controller("PagosController", function($scope, UserService, modal) {
	$scope.usuarios = {};
	$scope.requerimiento={};
	$scope.load=false;

	$scope.guardarUsuario = function() {
		$scope.load=true;
		$scope.request = {
			    header: {},
			    body: {
			        asunto: $scope.requerimiento.asunto,
			        usuarioDto: $scope.requerimiento.usuario
			    }

		};
		
		UserService.add("resources/registrarRequerimiento", $scope.request).then(
				function(response) {
					// modal.mensajeConfirmacion($scope,"SI INGRESO
					// CORRECTAMENTE",function(){},400);
					
					modal.mensaje("CONFIRMACION","SI INGRESO CORRECTAMENTE");
				}, function(error) {
					
					alert('Error');
				});
		
	};

});