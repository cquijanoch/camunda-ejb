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

		UserService.getAll("resources/expedientesDiga", null).then(
				function(response) {
					var tareasProceso = response.body.task !== null?response.body.task:[];
					settingRequerimientos.dataset = [];
					
					for (var i = 0; i < tareasProceso.length; i++) {
						
						var header = tareasProceso[i].headerDto;
		            	var requerimiento = tareasProceso[i].body;
		            	
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
		            	
						
						settingRequerimientos.dataset.push(data);
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

		UserService.getAll("resources/expedientesDiga/validarUsuario", null).then(
				function(response) {
					var tareasProcesoVU = response.body.task !== null?response.body.task:[];
					settingUsuario.dataset = [];
					
					for (var i = 0; i < tareasProcesoVU.length; i++) {
						var header = tareasProcesoVU[i].headerDto;
		            	var requerimiento = tareasProcesoVU[i].body;
		            	
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
		            	
						settingUsuario.dataset.push(data);
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
		            }
		};

		UserService.add("resources/expedientesDiga", request).then(
				function(response) {
					modal.mensaje("CONFIRMACION", "SE APROBO CORRECTAMENTE");
					eliminarElemento(settingRequerimientos.dataset,
							$scope.reqActual['i']);
					$scope.tabla1.reload();
				}, function(error) {
					alert('Error al guardar requerimiento');
				});
		
	};

	// aprobar usuarios
	$scope.reqActualUsua = {};

	$scope.validarUsuario = function(i, d) {
		$scope.reqActualUsua = d;
		$("#modalValidarUsuario").modal('show');
	};

	$scope.aprobarUsua = function(f) {

		$scope.reqActualUsua.estado = f;
		
		var request = {
		        header: {
		            executionId: $scope.reqActualUsua.executionId,
		            taskId: $scope.reqActualUsua.taskId
		        },
		        body: {
		            	'dni':$scope.reqActualUsua.dni,
		            	'nombre':$scope.reqActualUsua.nombre,
		            	'asunto':$scope.reqActualUsua.asunto,
		            	'estado':$scope.reqActualUsua.estado,
		            	'requerimientoId':$scope.reqActualUsua.requerimientoId,
		            	'usuarioId':$scope.reqActualUsua.usuarioId,
		            	'id':$scope.reqActualUsua.id,
		            }
		};
		
		UserService.add("resources/expedientesDiga/validarUsuario",request).then(
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
