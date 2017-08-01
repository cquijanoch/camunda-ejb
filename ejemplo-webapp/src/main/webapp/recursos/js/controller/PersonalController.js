app.controller("PersonalController",function($scope,UserService,modal,NgTableParams){
    
    var paramsTabla1 = {count: 10};
    var settingTabla1 = {counts: []};
    $scope.tabla1 = new NgTableParams(paramsTabla1, settingTabla1);
  
        
    $scope.listar1 = function(){
    	
    	settingTabla1.dataset = [{
			perId:12,    	
			perDNI:"34344512",
			perNom:"nfsdfs sfdjhsd",
			perAsu:"sdfksdjhf sdfkjshdfkjh",
			
    	},{
			perId:13,    	
			perDNI:"7456745",
			perNom:"jksdhfs sdjhfgsjdhf",
			perAsu:"TRAEAEEAERAR AEAE",
    	}];
        iniciarPosiciones(settingTabla1.dataset);            
        $scope.tabla1.settings(settingTabla1);
        
                
    };
    
    $scope.listar1();
    
    var paramsTabla2 = {count: 10}; 
    var settingTabla2 = {counts: []};
    $scope.tabla2 = new NgTableParams(paramsTabla2, settingTabla2);
        
    $scope.listar2 = function(){
    	
    	settingTabla2.dataset = [{
			perId:12,    	
			perDNI:"34344512",
			perNom:"nfsdfs sfdjhsd",
			perAsu:"sdfksdjhf sdfkjshdfkjh",
			detalle:"detalle1 sdfsdfsdfsdf",
    	},{
			perId:13,    	
			perDNI:"7456745",
			perNom:"jksdhfs sdjhfgsjdhf",
			perAsu:"TRAEAEEAERAR AEAE",
			detalle:"Detalle2",
    	}];
        iniciarPosiciones(settingTabla2.dataset);            
        $scope.tabla2.settings(settingTabla2);
        
                
    };
    
    $scope.listar2();
            
    $scope.reqActual = {};
    
    $scope.aprobarRequerimiento = function(i, d){    	
    	$scope.reqActual = d;
    	$("#modalAprobar").modal('show');
    	
    };
    
    $scope.aprobarReq = function(f){
    	$scope.reqActual.estado = f;    	
    };
    
    
    
    $scope.registrarRequerimiento = function(i, d){
    	$scope.reqActual = d;
    	$("#modalRegistrarReq").modal('show');    	
    };
    
    $scope.registrarReq = function(d){
    	$scope.reqActual.detalle = d;    	
    };

//    $scope.cerrarModalPariente = function(){
//        $scope.listar();
//        $('#modalEditar').modal('show');                
//    }
	
});
	