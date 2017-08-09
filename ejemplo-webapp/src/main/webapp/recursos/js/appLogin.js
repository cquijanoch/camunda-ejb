//creamos nuestro modulo llamado appLogin para logearnos
var app = angular.module("appLogin", ['ngRoute']);

app.constant('urls', {
    BASE: '/resources/login',
    BASECONTEXTO: '/ejemplo-webapp-0.0.1-SNAPSHOT/'
});
app.config(['$routeProvider','$httpProvider',function($routeProvider,$httpProvider) {
        
    //reiniciando las variables
    localStorage.clear();
    
    //interceptamos las peticiones
    $httpProvider.interceptors.push(['$q','$location',function($q,$location){
        return{
            'request': function(config){
                config.headers = config.headers || {};
                if(localStorage.getItem('jwt')){
                    config.headers.autorizacion = localStorage.getItem('jwt');
                }
                return config;
            },
            'responseError': function(response){
                if(response.status === 401 || response.status === 403 || response.status === 400){                   
                   localStorage.clear();
                   $location.path('/');
                }
                return $q.reject(response);
            }
        };
    }]);
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
        buscarUsuario: function(data,sucess,error){
            $http({
                method: 'POST',
                url: urls.BASE + "/login",
                headers: {'Content-Type': 'text/plain'},
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
    
    if($location.path() === '/')
        $location.path("#");

    function succesIniciarSession(response){
        $rootScope.bloquear=false;
        if( response.responseSta ){
            var objResponse = response.data;
            localStorage.setItem('jwt', objResponse.jwt);
            localStorage.setItem('usuario', window.btoa(JSON.stringify(objResponse.usuario)) );//JSON.stringify({usuarioID:objResponse.usuarioID,nombre:$rootScope.usuario.nombre}) );
            localStorage.setItem('rol', window.btoa(JSON.stringify(objResponse.rol)) );//JSON.stringify({roldID:$rootScope.rolSel.rolID,nombre:$rootScope.rolSel.nombre}));
            
            
            var funciones = [[],[],[],[],[],[]];
            objResponse.modulos.forEach(function(item){
                item.color = colores[item.moduloID-1];
                item.subModulos.forEach(function(sub){
                    sub.color = colores[sub.subModuloID-1];
                    sub.funciones.forEach(function(fun){
                        fun.color = colores[sub.subModuloID-1];
                        funciones[fun.tipo].push(fun);
                    });
                });                
            });
            localStorage.setItem('modulos', JSON.stringify(objResponse.modulos));
            localStorage.setItem('funciones', JSON.stringify(funciones));
            location.replace( urls.BASECONTEXTO + objResponse.url+"#menuInicio" );
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
        if(!$rootScope.session.organizacion || $rootScope.session.organizacion==null){
            servicioLogin.mensaje("MENSAJE","Seleccine una Organizacion");
            return;
        }
        if(!$rootScope.session.rolID || $rootScope.session.rolID==null){
            servicioLogin.mensaje("MENSAJE","Seleccine un Rol");
            return;
        }
        
        $rootScope.usuario.organizacionID = $rootScope.session.organizacion.organizacionID;
        $rootScope.usuario.rolID = $rootScope.session.rolID;

        var request = new Request($rootScope.usuario.nombre,'web');
        request.setCmd('login',1,'signin');
        //request.setMetadataValue('user.password','sh5');
        request.setData($rootScope.usuario);
        $rootScope.bloquear=true;
        servicioLogin.buscarUsuario(request,succesIniciarSession,errorIniciarSession);
    };
    $rootScope.identificarUsuario = function(){
        if(!$rootScope.usuario.nombre || $rootScope.usuario.nombre==""){
            servicioLogin.mensaje("MENSAJE","ingrese nombre de usuario");
            return;
        }
        var request = new Request($rootScope.usuario.nombre,'web');
        request.setCmd('login',1,'search');
        request.setData($rootScope.usuario);
        $rootScope.bloquear=true;
        servicioLogin.buscarUsuario(request,function(response){
            if( response.responseSta ){                
                $rootScope.organizaciones = response.data;
                $rootScope.session.organizacion = $rootScope.organizaciones[0];
                $rootScope.session.rolID = $rootScope.session.organizacion.roles[0].rolID;
                $rootScope.bloquear=false;
                location.replace( "#login" );                
                return;                
            }
            $rootScope.bloquear=false;
            $rootScope.session.mensaje = response.responseMsg;
        },function(){
            $rootScope.bloquear=false;
            console.log(response);
        });
        
    };
    $rootScope.recuperarPassword = function(){
        if(!$rootScope.usuario.nombre || $rootScope.usuario.nombre==""){
            servicioLogin.mensaje("MENSAJE","ingrese nombre de usuario");
            return;
        }
        var request = new Request($rootScope.usuario.nombre,'web');
        request.setCmd('login',1,'recuperar');
        request.setData($rootScope.usuario);
        $rootScope.bloquear=true;
        servicioLogin.recuperarPassword(request,function(response){
            if( response.responseSta ){               
                return;                
            }
            $rootScope.bloquear=false;
            $rootScope.session.mensaje = response.responseMsg;
        },function(){
            $rootScope.bloquear=false;
            console.log(response);
        });
        
    };
    $rootScope.seleccionarOrg = function(){
        $rootScope.session.rolID = $rootScope.session.organizacion.roles[0].rolID;
    }
    $rootScope.regresar = function(){
        $rootScope.organizaciones = [];
        $rootScope.usuario={nombre:"",password:""};
        $rootScope.session = {mensaje:""};
        location.replace( "#regresar" );
        return;
    };  
    
    $rootScope.validarCodigo = function() {
        if($rootScope.objConfirm.codGen === $rootScope.objConfirm.codIng) {
            var request = new Request($rootScope.objConfirm.parCod, 'web');
            request.setCmd('login', 1, 'registerCompetitor');
            request.setData({t: "P", m: $rootScope.objConfirm.tipPub, p: $rootScope.objConfirm.parCod, s: $rootScope.objConfirm.sedCod});
            
            servicioLogin.buscarUsuario(request, function(success){
                if (success.response === 'OK') {
                    if(success.data.expiration) {
                        $rootScope.objConfirm.men = "El periodo para confirmar su asistencia la curso de capacitación ha finalizado. Por favor contactar al organizador.";
                        $rootScope.objConfirm.statePublicCod = false;
                        $rootScope.objConfirm.stateSelection = true;
                    } else if(success.data.registered) {
                        $rootScope.objConfirm.men = "Ud. ya forma parte de la capacitación en cuestión";
                        $rootScope.objConfirm.statePublicCod = false;
                        $rootScope.objConfirm.stateSelection = true;
                    } else {
                        var cap = success.data.cap; 
                        $rootScope.objConfirm.cap = new Object();
                        $rootScope.objConfirm.cap.nom = cap.nom;
                        $rootScope.objConfirm.cap.tip = cap.tip;
                        $rootScope.objConfirm.cap.cro = cap.cro;

                        $rootScope.objConfirm.par = new Object();
                        $rootScope.objConfirm.par.ter = false;
                        $rootScope.objConfirm.estVer = false;
                        
                        if($rootScope.objConfirm.tipPub) {
                            var par = success.data.cap.per; 
                            $rootScope.objConfirm.par.nom = par.nom;
                            $rootScope.objConfirm.par.pat = par.pat;
                            $rootScope.objConfirm.par.mat = par.mat;
                            $rootScope.objConfirm.parEst = false;                            
                        } else {
                            $rootScope.objConfirm.org = success.data.cap.org;
                            $rootScope.objConfirm.parEst = true;
                        }

                        $rootScope.objConfirm.statePublicDat = true;
                    }
                } else if (success.response === 'BAD')
                    modal.mensaje("ERROR", success.responseMsg);
            },function(error){
                modal.mensaje("MENSAJE", error.responseMsg);
            });
        } else
            servicioLogin.mensaje("ERROR","El código ingresado es incorrecto. Intente otra vez");
    }; 
    $rootScope.$watch('objConfirm.par.dni', function(newValue) {
        if(newValue !== undefined) {
            var request = new Request($rootScope.objConfirm.parCod, 'web');
            request.setCmd('login', 1, 'registerCompetitor');
            request.setData({t: "B", s: $rootScope.objConfirm.sedCod, d: newValue});
            
            servicioLogin.buscarUsuario(request, function(success){
                if (success.response === 'OK') {
                    if(success.data.expiration) {
                        $rootScope.objConfirm.men = "El periodo para confirmar su asistencia la curso de capacitación ha finalizado. Por favor contactar al organizador.";
                        $rootScope.objConfirm.statePublicCod = false;
                        $rootScope.objConfirm.stateSelection = true;
                    } else if(success.data.available) {
                        $rootScope.objConfirm.parEst = false;
                        $rootScope.objConfirm.estVer = false;
                    } else {
                        $rootScope.objConfirm.parEst = true;
                        $rootScope.objConfirm.estVer = true;
                        
                        $rootScope.objConfirm.par.nom = '';
                        $rootScope.objConfirm.par.pat = '';
                        $rootScope.objConfirm.par.mat = '';
                        $rootScope.objConfirm.par.org = '';
                        $rootScope.objConfirm.par.ter = false;
                        
                        $rootScope.objConfirm.men = "Ud. ya se forma parte del sistema como usuario. Contactar al administrador para mayor información";
                    }
                } else if (success.response === 'BAD')
                    modal.mensaje("ERROR", success.responseMsg);
            },function(error){
                modal.mensaje("MENSAJE", error.responseMsg);
            });
        }
    });
    $rootScope.registrarParticipante = function() {
        var request = new Request($rootScope.objConfirm.parCod, 'web');
        request.setCmd('login', 1, 'registerCompetitor');
        
        if($rootScope.objConfirm.tipPub)
            request.setData({t: "A", p: $rootScope.objConfirm.parCod, a: $rootScope.objConfirm.par, s: $rootScope.objConfirm.sedCod});
        else
            request.setData({t: "N", a: $rootScope.objConfirm.par, c: $rootScope.objConfirm.parCor, s: $rootScope.objConfirm.sedCod});
        
        servicioLogin.buscarUsuario(request,function(success){
            if (success.response === 'OK') {
                if(success.data.expiration) 
                    $rootScope.objConfirm.men = "El periodo para confirmar su asistencia la curso de capacitación ha finalizado. Por favor contactar al organizador.";
                else if(success.data.registered) 
                    $rootScope.objConfirm.men = "Ud. ya forma parte de la capacitación en cuestión";
                else
                    $rootScope.objConfirm.men = "Ud. acaba de ser registrado. Ya es parte de la capacitación FELICIDADES !";
                 
                $rootScope.objConfirm.statePublicCod = false;
                $rootScope.objConfirm.stateSelection = true;
            } else if (success.response === 'BAD')
                modal.mensaje("ERROR", success.responseMsg);
        },function(error){
            modal.mensaje("MENSAJE", error.responseMsg);
        });
    }; 
}]);

/* Claves del REQUEST*/
KEY_REQUEST_STR = "i.type";
REQUEST_STR = "inet-req";
REQUEST_ID_STR = "cmd";
REQUEST_IDENTITY_STR = "identity";
REQUEST_SCOPE_STR = "scope";
REQUEST_META_STR = "meta";
REQUEST_DATA_STR = "data";

function Request(identity,scope){

    this[KEY_REQUEST_STR] = REQUEST_STR;
    this[REQUEST_SCOPE_STR] = scope;
    this[REQUEST_IDENTITY_STR] = identity;
    this[REQUEST_META_STR] = new Object();

    this.getCmd = function(){
        return this.mCurrendCommand;    
    };
    this.setCmd = function(dominio,version,accion){
        this[REQUEST_ID_STR] = dominio+"@"+version+":"+accion;
    };
    this.getIdentity = function(){
        return this.mIdentity;
    };
    this.setIdentity = function(identity){
        this[REQUEST_IDENTITY_STR] = identity;
    };
    this.getScope = function(){
        return this.mScope;
    };
    this.setScope = function(scope){
        this[REQUEST_SCOPE_STR] = scope;
    };
    this.getData = function(){
        return this.mData;
    };
    this.setData = function(data){
        this[REQUEST_DATA_STR] = data;
    };
    this.getMetadata = function(){
        return this[REQUEST_META_STR];
    };
    this.setMetadataValue = function(key,value){
        if(!(value instanceof Array))
            this[REQUEST_META_STR][key] = [value];
    };    
    this.setMetadataValues = function(key,values){
        if(values instanceof Array)
            this[REQUEST_META_STR][key] = values;
    };
};
