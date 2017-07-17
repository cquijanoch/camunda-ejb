
app.factory("DemoConfig",function(){
	
	return {
		demoUrlService:'/demo/',
	}
	
});

app.factory("SendRequest",function($http,$q,DemoConfig){
	return{
		noAuthenticatedRequest:function(_method,_url,_data){
			var deferred = $q.defer();
			var url = DemoConfig.demoUrlService + _url;
			var request = {
					method: _method,
					data: _data,
					url:url
			};
		
			$http(request)
			.success(function(response,status,headers){
				JSON.stringify(deferred.resolve(response));
			})
			.error(function(response){
				deferred.reject(response);
			})
			return  deferred.promise;
		}
	}
});

app.factory('modal', ['$compile','$interpolate', function($compile,$interpolate){
    return{
        mensaje: function(titulo,mensaje){
            $.gritter.add({
                // (string | mandatory) the heading of the notification
                title: titulo,
                // (string | mandatory) the text inside the notification
                text: mensaje,
                // (string | optional) the image to display on the left
                //image: 'assets/img/ui-sam.jpg',
                // (bool | optional) if you want it to fade out on its own or just sit there
                sticky: false,
                // (int | optional) the time you want it to be alive for before fading out
                time: '2000',
                // (string | optional) the class name you want to apply to that specific message
                class_name: 'my-sticky-class'
            });
        },
        mensajeConfirmacion: function($scope,mensaje,funcion,width){
            
            if(!width)
                width = '750';//tamaño por default
            
            $scope.miFuncionConfirmacion = funcion;
            $compile('<confirmacion mensaje="'+mensaje+'" accion="miFuncionConfirmacion" contenido width="'+width+'"></confirmacion>')($scope);
            
        },
        mensajeConfirmacion2: function($scope,titulo,funcion,contenido,width){
            
            if(!width)
                width = '750';//tamaño por default
            
            $scope.miFuncionConfirmacion = funcion;
            $compile('<confirmacion titulo="'+titulo+'" mensaje="" accion="miFuncionConfirmacion" contenido="'+contenido+'" width="'+width+'"></confirmacion>')($scope);
            
        }
    };
}]);     