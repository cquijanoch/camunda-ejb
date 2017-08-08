app.controller("UserController",function($scope,$rootScope,UserService,modal,$location){
	
	$scope.roles = [{id:2,nombre:'Analista de Planillas'},
					{id:1,nombre:'Recursos Humanos'}
					];

	$scope.sel="";
	$scope.ingresar=function(){
		$rootScope.rol=$scope.sel;
		
		if($rootScope.rol===2)
			$location.path("planillas");
		if($rootScope.rol===1)
			$location.path("personal");
		
		
	};
	
});
	