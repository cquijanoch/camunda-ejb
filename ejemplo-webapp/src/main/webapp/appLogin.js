//creamos nuestro modulo llamado appLogin para logearnos
var app = angular.module("appLogin", ['ngRoute']);

app.constant('urls', {
    BASE: '/ejemplo-webapp-0.0.1-SNAPSHOT/resources/login',
    BASECONTEXTO: '/ejemplo-webapp-0.0.1-SNAPSHOT/'
});
app.config(['$routeProvider','$httpProvider',function($routeProvider,$httpProvider) {
        
    //reiniciando las variables
    localStorage.clear();
    
    //hacemos el ruteo de nuestra aplicaciÃ³n
    $routeProvider.when("/", {
            templateUrl : "login1.html"
    })
    .when("/login", {
        templateUrl : "login2.html"
    })
    .otherwise({ redirectTo : "/"});
}]);
app.factory('servicioLogin', ['$http','urls', function($http,urls){
    return{
        buscarUsuario: function(data,url,sucess,error){
            $http({
                method: 'POST',
                url: urls.BASE + url,
                data: data
            }).success( sucess ).error( error);
        },
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
                time: '2500',
                // (string | optional) the class name you want to apply to that specific message
                class_name: 'my-sticky-class'
            });
        }
    };
}]);

var colores = ["#fa8564","#1fb5ad","#9466b5","#58C9F3","#CE93D8","#90CAF9","#4DB6AC","#1DE9B6","#DCE775","#EEFF41","#FF9800","#BDBDBD","#F48FB1","#3F51B5","#006064","#FF6F00","#8D6E63","#B71C1C","#FFEE58","#607D8B","#FFF176","#43A047","#FF5722"];
app.run(['$rootScope','$location','servicioLogin','urls','$routeParams', function($rootScope,$location,servicioLogin,urls,$routeParams){

    $rootScope.usuario = {nombre:"",password:""};
    $rootScope.session = {mensaje:""};
    $rootScope.grupos = [];
    
    if($location.path() === '/')
        $location.path("#");

    function succesIniciarSession(response){
        $rootScope.bloquear=false;
        if( response.usuario!=null ){
            localStorage.setItem('jwt', response.jwt);
            localStorage.setItem('usuario', window.btoa(JSON.stringify(response.usuario)) );//JSON.stringify({usuarioID:objResponse.usuarioID,nombre:$rootScope.usuario.nombre}) );
            localStorage.setItem('rol', window.btoa(JSON.stringify(response.rolId)) );//JSON.stringify({roldID:$rootScope.rolSel.rolId,nombre:$rootScope.rolSel.nombre}));
            
            if(response.rolId==1)
            {
            	var rol="#personal";
            } else
            	if(response.rolId==2)
                {
                	var rol="#planilla";
                } else if(response.rolId==3)
                {
                	var rol="#tesoreria";
                }else if(response.rolId==4)
                {
                	var rol="#pagos";
                }
            location.replace( urls.BASECONTEXTO +"app/"+rol );
            return;
        }
        servicioLogin.mensaje("MENSAJE",response.responseMsg);
    };
    function errorIniciarSession(response){
        $rootScope.bloquear=false;
        console.log(response);
    };
    $rootScope.iniciarSession = function(){
        
        if(!$rootScope.usuario.password || $rootScope.usuario.password==""){
            servicioLogin.mensaje("MENSAJE","ingrese su password");
            return;
        }
     
        if(!$rootScope.session.rolId || $rootScope.session.rolId==null){
            servicioLogin.mensaje("MENSAJE","Seleccine un Rol");
            return;
        }
        
        $rootScope.usuario.rolId = $rootScope.session.rolId;

        $rootScope.bloquear=true;
        servicioLogin.buscarUsuario($rootScope.usuario,"/signin",succesIniciarSession,errorIniciarSession);
    };
    $rootScope.identificarUsuario = function(){
        if(!$rootScope.usuario.nombre || $rootScope.usuario.nombre==""){
            servicioLogin.mensaje("MENSAJE","ingrese nombre de usuario");
            return;
        }
        
        $rootScope.bloquear=true;
        servicioLogin.buscarUsuario($rootScope.usuario,"/search",function(response){
            if( response.length>0 ){                
                $rootScope.grupos = response;
                $rootScope.session.organizacion = $rootScope.grupos[0];
                $rootScope.session.rolId = $rootScope.session.organizacion.rolId;
                $rootScope.bloquear=false;
                location.replace( "#login" );                
                return;                
            }
            $rootScope.bloquear=false;
            $rootScope.session.mensaje = "Usuario no Registrado";
        },function(){
            $rootScope.bloquear=false;
            console.log(response);
        });
        
    };
    
    $rootScope.seleccionarOrg = function(){
        $rootScope.session.rolId = $rootScope.session.organizacion.rolId;
    }
    $rootScope.regresar = function(){
        $rootScope.grupos = [];
        $rootScope.usuario={nombre:"",password:""};
        $rootScope.session = {mensaje:""};
        location.replace( "#regresar" );
        return;
    };  
    

}]);
