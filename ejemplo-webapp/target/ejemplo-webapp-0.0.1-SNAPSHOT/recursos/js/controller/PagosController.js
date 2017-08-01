app.controller("PagosController", function($scope, UserService, modal) {
	$scope.usuarios = {};

	$scope.usuarios = {
		idUsuario : "",
		nombre : "",
		dni : ""
	};

	$scope.guardarUsuario = function() {
		
		$scope.data = $scope.usuarios;
		UserService.add("resources/registrarUsuario", $scope.data).then(
				function(response) {
					modal.mensajeConfirmacion("CONFIRMACION","SI INGRESO CORRECTAMENTE");
				}, function(error) {
					alert('Error');
				});

	};
});