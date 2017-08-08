app.controller("PersonalController",function($scope,UserService,modal,NgTableParams){
    
    var paramsTabla1 = {count: 10};
    var settingTabla1 = {counts: []};
    $scope.tabla1 = new NgTableParams(paramsTabla1, settingTabla1);
  
        
    $scope.listar1 = function(){
        
        UserService.getAll("resources/mesaPartes/bandejaSinRevision").then(
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
   
    
    var paramsTabla2 = {count: 10}; 
    var settingTabla2 = {counts: []};
    $scope.tabla2 = new NgTableParams(paramsTabla2, settingTabla2);
        
    $scope.listar2 = function(){
        
        UserService.getAll("resources/mesaPartes/bandejaAprobados").then(
				function(response){
					settingTabla2.dataset = response;
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
    	
    	if(f=='A')
    	{
    		UserService.update("resources/mesaPartes/aprobarRequisito", $scope.reqActual).then(
    				function(response){
    					modal.mensaje("CONFIRMACION","Se Aprobo Correctamente");
    					$scope.listar1();
    					$scope.listar2(); 
    				},
    				function(error){
    					alert('Error');
    				}
    		);
    	}
    	
    	if(f=='D')
    	{
    		UserService.update("resources/mesaPartes/desaprobarRequisito", $scope.reqActual).then(
    				function(response){
    					modal.mensaje("CONFIRMACION","Se Desaprobo Correctamente");
    					$scope.listar1();
    					$scope.listar2(); 
    				},
    				function(error){
    					alert('Error');
    				}
    		);
    	}
    	
    	    	
    };
    
    
    
    $scope.registrarRequerimiento = function(i, d){
    	$scope.reqActual = d;
    	$("#modalRegistrarReq").modal('show');    	
    };
    
    $scope.registrarReq = function(d){
    	$scope.reqActual.detalle = d;   
    	delete $scope.reqActual['i'];
    	UserService.update("resources/mesaPartes/registrarRequerimiento", $scope.reqActual).then(
				function(response){
					modal.mensaje("CONFIRMACION","Se Registro Correctamente");
					$scope.listar2(); 
					$scope.reqActual = {};
					
				},
				function(error){
					alert('Error');
				}
		);
    };

//    $scope.cerrarModalPariente = function(){
//        $scope.listar();
//        $('#modalEditar').modal('show');                
//    }
	
});
	