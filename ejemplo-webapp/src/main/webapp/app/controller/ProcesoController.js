app.controller("ProcesoController", function($scope, UserService, modal,$routeParams,BPMService) {
				    
	
	var BpmnViewer = window.BpmnJS;
	var viewer = new BpmnViewer({container: '#diagramCanvas', width: '100%', height: '100%'});

	var container = $('#js-drop-zone');
	
	$scope.process = {
			'processInstanceId' : $routeParams.processInstanceId
	}
	
	UserService.getAll("resources/history/process-instance/"+$scope.process.processInstanceId,null).then(
			function(response){
				$scope.process.processDefinitionId = response.processDefinitionId;
				UserService.getAll("resources/process-definition/xml/"+response.processDefinitionId,null).then(
						function(response){
							
							viewer.importXML(response.bpmn20Xml, function(err) {
								if (err) {
									console.log('error rendering', err);
								} else {
									var canvas = viewer.get('canvas');
									var overlays = viewer.get('overlays');

									container.removeClass('with-error')
											 .addClass('with-diagram');
									// zoom to fit full viewport
									canvas.zoom('fit-viewport');
								}
								
								UserService.getAll( 'resources/process-instance/activity-instances/' + $scope.process.processInstanceId).then(
										function(response){
											BPMService.addMarkerForActivities(canvas, response);
										},
										function(error){
											
										}
								);
								
								UserService.getAll( 'resources/history/activity-instance/' + $scope.process.processInstanceId).then(
										function(response){
											BPMService.addHistoryInfoOverlay(overlays, response)
										},
										function(error){
											
										}
								);
								    
							});
							
						},
						function(error){
							
						}
				);

			}
			,function(error){}
	)
})