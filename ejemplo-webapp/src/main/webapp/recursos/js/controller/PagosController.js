app.controller("PagosController", function($scope, UserService, modal) {
	$scope.usuarios = {};

	$scope.usuarios = {
		nombre : "",
		dni : "",
		asunto : ""
	};

	$scope.guardarUsuario = function() {
		
		$scope.request = {
			    body: {
			        nombre: $scope.usuarios.nombre,
			        dni: $scope.usuarios.dni,
			        asunto:$scope.usuarios.asunto,
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