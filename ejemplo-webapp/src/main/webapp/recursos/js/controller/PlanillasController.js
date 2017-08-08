app.controller("PlanillasController", function($scope, UserService, modal,
		NgTableParams) {

	var paramsRequerimientos = {
		count : 10
	};
	var settingRequerimientos = {
		counts : []
	};
	$scope.tabla1 = new NgTableParams(paramsRequerimientos,
			settingRequerimientos);

	$scope.listarRequerimientos = function() {

		UserService.getTasks("resources/RequemientoDiga", null).then(
				function(response) {
					var tareasProceso = response.body.task;
					settingRequerimientos.dataset = [];
					for (var i = 0; i < tareasProceso.length; i++) {
						settingRequerimientos.dataset
								.push(tareasProceso[i].headerDto);
					}
					iniciarPosiciones(settingRequerimientos.dataset);
					$scope.tabla1.settings(settingRequerimientos);
				}, function(error) {
					console.log('Error');
				});

	};
	$scope.listarRequerimientos();

	/*-----------------pestaña validar usuario listar---------------------*/

	var paramsUsuario = {
		count : 10
	};
	var settingUsuario = {
		counts : []
	};
	$scope.tabla2 = new NgTableParams(paramsUsuario, settingUsuario);

	$scope.listarUsuarios = function() {

		UserService.getTasks("resources/ValidarUsuarioDiga", null).then(
				function(response) {
					var tareasProcesoVU = response.body.task;
					settingUsuario.dataset = [];
					for (var i = 0; i < tareasProcesoVU.length; i++) {
						settingUsuario.dataset
								.push(tareasProcesoVU[i].headerDto);
					}
					iniciarPosiciones(settingUsuario.dataset);
					$scope.tabla2.settings(settingUsuario);
				}, function(error) {
					console.log('Error');
				});
	};

	$scope.listarUsuarios();

	$scope.reqActual = {};

	$scope.aprobarRequerimiento = function(i, d) {
		$scope.reqActual = d;
		$("#modalRevisarRequerimiento").modal('show');
	};

	// aprobar requerimiento
	$scope.aprobarReq = function(f) {

		$scope.reqActual.aprobado = f;

		$scope.request = {
			header : {
				executionId : $scope.reqActual.executionId,
				taskId : $scope.reqActual.taskId
			},
			body : {
				aprobado : f
			}
		};

		UserService.add("resources/RequemientoDiga", $scope.request).then(
				function(response) {
					modal.mensaje("CONFIRMACION", "SE APROBO CORRECTAMENTE");
					eliminarElemento(settingRequerimientos.dataset,
							$scope.reqActual['i']);
					$scope.tabla1.reload();
				}, function(error) {
					alert('Error al guardar requerimiento');
				});
		console.log($scope.request.header);
	};

	// aprobar usuarios
	$scope.reqActualUsua = {};

	$scope.validarUsuario = function(i, d) {
		$scope.reqActualUsua = d;
		$("#modalValidarUsuario").modal('show');
	};

	$scope.aprobarUsua = function(f) {

		$scope.reqActualUsua.aprobado = f;

		$scope.request = {
			header : {
				executionId : $scope.reqActualUsua.executionId,
				taskId : $scope.reqActualUsua.taskId
			},
			body : {
				aprobado : f
			}
		};

		UserService.add("resources/ValidarUsuarioDiga", $scope.request).then(
				function(response) {
					modal.mensaje("CONFIRMACION", "SE APROBO CORRECTAMENTE");
					eliminarElemento(settingUsuario.dataset,
							$scope.reqActualUsua['i']);
					$scope.tabla2.reload();
				}, function(error) {
					alert('Error al guardar usuario');
				});
	};

});