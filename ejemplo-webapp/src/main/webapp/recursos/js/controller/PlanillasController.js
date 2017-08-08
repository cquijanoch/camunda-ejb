app.controller("PlanillasController",function($scope,UserService,modal,NgTableParams){
    
    var paramsTabla1 = {count: 10};
    var settingTabla1 = {counts: []};
    $scope.tabla1 = new NgTableParams(paramsTabla1, settingTabla1);
    	
        $scope.listar1 = function(){    
            UserService.getAll("resources/expedientesDiga/bandejaSinRevision").then(
    				function(response){
    					settingTabla1.dataset = response;
    		            iniciarPosiciones(settingTabla1.dataset);
    		            $scope.tabla1.settings(settingTabla1);
    				},
    				function(error){
    					alert('Error');
    				}
    		);       
        };                
    
    $scope.reqActual = {};
    
    $scope.aprobarRequerimiento = function(i, d){    	
    	$scope.reqActual = d;
    	$("#modalRevisarRequerimiento").modal('show');
    	
    };
    
    $scope.validarUsuario = function(i, d){    	
    	$scope.reqActual = d;
    	$("#modalValidarUsuario").modal('show');
    	
    };
    
    $scope.aprobarReq = function(f){
    	
    	$scope.reqActual.estado = f;    
    	delete $scope.reqActual['i'];
    	if(f=='A')
    	{
    		UserService.update("resources/expedientesDiga/aprobarRequisito", $scope.reqActual).then(
    				function(response){
    					modal.mensaje("CONFIRMACION","Se Aprobo Correctamente");
    				},
    				function(error){
    					alert('Error');
    				}
    		);
    	}
    	if(f=='D')
    	{
    		UserService.update("resources/expedientesDiga/desaprobarRequisito", $scope.reqActual).then(
    				function(response){
    					modal.mensaje("CONFIRMACION","Se Desaprobo Correctamente");
    				},
    				function(error){
    					alert('Error');
    				}
    		);
    	}
    	
    	    	
    };
    
    $scope.aprobarUsuario = function(f){
    	
    	$scope.reqActual.estado = f;    
    	delete $scope.reqActual['i'];
    	if(f=='A')
    	{
    		UserService.update("resources/expedientesDiga/aprobarUsuario", $scope.reqActual).then(
    				function(response){
    					console.log("se aprobo el requerimiento");
    					$scope.listar1();
    					modal.mensaje("CONFIRMACION","Se Aprobo Correctamente");
    				},
    				function(error){
    					alert('Error');
    				}
    		);
    	}
    	if(f=='D')
    	{
    		UserService.update("resources/expedientesDiga/desaprobarUsuario", $scope.reqActual).then(
    				function(response){
    					console.log("se aprobo el requerimiento");
    					$scope.listar1();
    					modal.mensaje("CONFIRMACION","Se Desaprobo Correctamente");
    				},
    				function(error){
    					alert('Error');
    				}
    		);
    	}
    	
    	    	
    };
    
    
    
	
});
	