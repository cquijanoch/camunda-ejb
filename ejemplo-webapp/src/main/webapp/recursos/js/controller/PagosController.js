app.controller("PagosController", function($scope, UserService, modal) {
	$scope.usuarios = {};
	$scope.requerimiento={};
	

	$scope.guardarUsuario = function() {
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
	
	UserService.getTasks("resources/registrarRequerimiento",null).then(
			function(response){
				console.log('Exito')
			},
			function(error){
				console.log('Error');
			}
	)

});