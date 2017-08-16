app.controller("ProcesoController", function($scope, UserService, modal,$location) {
				    
	
	$scope.verProceso= function(){
		$location.path('/proceso/' + $scope.process.processInstanceId);
	}
})