app.service("BPMService",function(){
	
	this.addMarkerForActivities = function(canvas, actInstTree) {
		if (actInstTree.childTransitionInstances.length==0 && actInstTree.childActivityInstances.length==0) {
			canvas.addMarker(actInstTree.activityId, 'highlight');	
		}
		else {
			for (index=0; index < actInstTree.childTransitionInstances.length; ++index) {
				canvas.addMarker(actInstTree.childTransitionInstances[index].activityId, 'highlight');	
			}
			for (index=0; index < actInstTree.childActivityInstances.length; ++index) {
			    // add recursively
				this.addMarkerForActivities(canvas, actInstTree.childActivityInstances[index]);	
			}
		}
	};
	
	this.addHistoryInfoOverlay = function(overlays, actInstList) {
	
       for (index = 0; index < actInstList.length; ++index) {
			var calledPiLink = '';
			var finished = '';
			if (actInstList[index].endTime) {
				finished = '<i class="icon-ok icon-white"></i>';
			}
			if (actInstList[index].calledCaseInstanceId) {
				calledPiLink = '<a href="cmmn.html?caseInstanceId=' + actInstList[index].calledCaseInstanceId + '"><i class="icon-circle-arrow-right icon-white"></i></a>';
			}		
			if (actInstList[index].calledProcessInstanceId) {
				calledPiLink = '<a href="bpmn.html?processInstanceId=' + actInstList[index].calledProcessInstanceId + '"><i class="icon-circle-arrow-right icon-white"></i></a>';
			}
			if (finished || calledPiLink) {
				overlays.add(actInstList[index].activityId, {
				  position: {
				    top: 0,
				    right: 0
				  },
				  html: '<div class="bpmn-badge">'+ finished + calledPiLink+'</div>'
				});
			}					        
       }
     };
	
})