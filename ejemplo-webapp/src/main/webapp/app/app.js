var app= angular.module("demoApp",[
	'ngRoute','treeControl', 'ngTable', 'ngAnimate','ui.bootstrap','chart.js','ngMaterial'
]);


   
app.config(function($routeProvider){
	
	$routeProvider
	 .when('/menuInicio', {
 		  templateUrl: 'views/menuInicio.html',
     })
     .when("/subMenuInicio", {
        templateUrl : "subMenuInicio.html"
     })
     .when('/personal', {
    	 controller: 'PersonalController',
         templateUrl : 'views/mesaPartes.html'//mesa de partes
     })
     .when('/pagos', {
    	 controller: 'PagosController',
         templateUrl : 'views/usuario.html'//usuario
     })
     .when('/planillas', {
    	 controller: 'PlanillasController',
         templateUrl : 'views/diga.html'//diga
     })
     .when('/tesoreria', {
    	 controller: 'TesoreriaController',
         templateUrl : 'views/tesoreria.html'//tesoreria
     })
     .when('/procesos', {
    	 controller: 'ProcesoController',
         templateUrl : 'views/procesos.html'//tesoreria
     })
     .when('/proceso/:processInstanceId', {
    	 controller: 'ProcesosController',
         templateUrl : 'views/proceso.html'//tesoreria
     })
     .otherwise( {
         redirectTo: '/'
      } );
});


app.run(['$rootScope','$location',function($rootScope,$location) {
    
    if( window.innerWidth < 479 ){
        $rootScope.movil = true;
    }
        
    $rootScope.usuMaster = {usuario:"",rol:"",organizacion:""};
    $rootScope.menuPrincipal = [];
    $rootScope.rol=JSON.parse( window.atob(localStorage.getItem('rol')) );
    
    $rootScope.noticias = [{hora:"0:0",titulo:"noticia nueva",descripcion:"descripcion"},{hora:"0:0",titulo:"noticia nueva",descripcion:"descripcion"}];
    $rootScope.notificaciones = [];//[{tipo:"alert-info",nombre:"notificacino de alerta"},{tipo:"alert-danger",nombre:"notificacino de peligro"},{tipo:"alert-warning",nombre:"notificacino de exito"}];
    $rootScope.mensajes = [];//[{nombre:"profesor",hora:"10:00",contenido:"hola como estas"},{nombre:"profesor",hora:"10:00",contenido:"hola como estas"}];
    
    
    $rootScope.menu = [
    	{nombre:"Mesa de Partes",id:"1"},
    	{nombre:"DIGA",id:"2"},
    	{nombre:"Tesoreria",id:"3"},
    	{nombre:"Usuario",id:"4"}
    	];
    $rootScope.modNom = "";
    $rootScope.subModNom = "";
    $rootScope.visNom = "";    
    //configuracion inicial "no tocar"
    
    $rootScope.cerrarSession = function (){
        localStorage.clear();
        location.replace('/ejemplo-webapp-0.0.1-SNAPSHOT/');
    };
    
    $rootScope.inicio = function(){
        $rootScope.menu = [];
        $rootScope.modNom = "";
        $rootScope.subModNom = "";
        $rootScope.visNom = "";
        $location.path("menuInicio");
    };
        
    $rootScope.elegirMenu = function(menu){        
        $rootScope.menu = menu.subModulos;
        $rootScope.modNom = $rootScope.movil? menu.codigo: menu.nombre;
        localStorage.setItem('menu', JSON.stringify($rootScope.menu));
        localStorage.setItem('modNom', $rootScope.modNom);
        $rootScope.subModNom = "";
        $rootScope.visNom = "";
        $rootScope.color = menu.color;
        $location.path("subMenuInicio");
    };
    $rootScope.menuToggle = function(e,subModulo){
        if(subModulo.funciones){
            if($rootScope.subModNom == ($rootScope.movil? subModulo.codigo: subModulo.nombre)){
                $rootScope.subModNom = "";
                localStorage.setItem('subModNom', "");
                $location.path("subMenuInicio");
            }
            else{                
                $rootScope.subModNom = $rootScope.movil? subModulo.codigo: subModulo.nombre;
                localStorage.setItem('subModNom', $rootScope.subModNom);
            }
            $rootScope.visNom = "";            
        }
    };
    $rootScope.elegirVista = function(vista,subModulo){
        $rootScope.visNom = vista.nombre;
        localStorage.setItem('visNom', JSON.stringify($rootScope.visNom));
        if(subModulo){
            $rootScope.subModNom = $rootScope.movil? subModulo.codigo: subModulo.nombre;;
            localStorage.setItem('subModNom', $rootScope.subModNom);
        }
        else{
            //ocultar siempre que elijamos una opcion, solo para moviles
            if(window.innerWidth < 981){
                $('#sidebar').toggleClass('hide-left-bar');
                $('#main-content').toggleClass('merge-left');
            }
        }
        $location.path(vista.clave);
    };
    $rootScope.ocultarMenu = function(){
        $('#sidebar').toggleClass('hide-left-bar');
        $('#main-content').toggleClass('merge-left');
    };
    $rootScope.mostrarMenuDerecha = function(){
        $('.right-sidebar').toggleClass('hide-right-bar');
        $('#main-content').toggleClass('merge-right');
    };
    $rootScope.verAyuda = function(){
        
        var o = buscarObjeto($rootScope.menu,'nombre',$rootScope.subModNom);
        if(o){
            $rootScope.imgAyuda = o.codigo.trim()+'.png';
            $('#modalAyuda').modal('show');
        }
    };

    
}]);