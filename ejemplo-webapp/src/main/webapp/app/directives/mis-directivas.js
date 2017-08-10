'use strict';


app.directive('confirmacion',['$compile', function ($compile) {
return {
    replace: true,
    restrict: 'E',
    scope: {
        titulo:'@',
        contenido:'@',
        mensaje:'@',
        accion: '=',
        width:'@'
    },
    template:
            '<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'+
                '<div class="modal-dialog dlg-confirmacion" style="max-width:{{width}}px">'+
                  '<div class="modal-content">'+
                    '<div class="modal-header">'+
                      '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'+
                      '<h4 class="modal-title" id="myModalLabel">{{titulo?titulo:"DIALOGO DE CONFIRMACION"}}</h4>'+
                    '</div>'+
                    '<div class="modal-body">{{mensaje}}</div>'+
                    '<div class="modal-footer">'+
                      '<button ng-if="accion" type="button" class="btn btn-default" ng-click="funcionNo()"><i class="fa fa-times" ></i> No</button>'+
                      '<button ng-if="accion" type="button" class="btn btn-warning" ng-click="funcionSi()"><i class="fa fa-check" ></i> Si</button>'+
                      '<button ng-if="!accion" type="button" class="btn btn-warning" ng-click="funcionNo()"><i class="fa fa-check" ></i> Aceptar</button>'+
                    '</div>'+
                  '</div>'+
                '</div>'+
              '</div>'
      ,
      link: function(scope,elem,attrs){
          
          scope.funcionSi = function(){
              
              scope.accion();
              elem.modal('hide');
              setTimeout(function(){ elem.remove(); }, 1000);
              
              delete scope.accion;
          };
          scope.funcionNo = function(){
              elem.modal('hide');
              setTimeout(function(){ elem.remove(); }, 1000);
              //;
          };
          
        if(scope.contenido)
            elem.find('.modal-body').append($compile(scope.contenido)(scope));
          
        $("#main-content").append(elem);
        elem.modal('show'); 
      }
    };
}]);


/*Componente para subir un archivo*/
app.directive('inputFile',[ function () {
return {
    restrict: 'E',
    scope: {
        archivo: '=',
        nombre: '=',
        nover: '='
    },
    template:
            '<input type="file" class="mi-input" onchange="angular.element(this).scope().cambiarMiArchivo(this)"></input><span ng-if="!nover" style="margin-top: 4px;">{{archivo.name}}</span>'
    ,
    link: function(scope,elem,attrs){
          scope.cambiarMiArchivo = function(input){
            scope.$apply(function () {
                if(input.files[0]){
                    scope.archivo = crearMyFile(input.files[0]);
                    scope.nombre = scope.archivo.name;
                }
                else{
                    scope.archivo = null;
                    scope.nombre = "";
                }
            });
              
              /*input.files[0] = {};//reiniciando el archivo*/
          };
      }
    };
}]);
/*Componente para ver un archivo*/
app.directive('viewFile',[ function () {
return {
    restrict: 'E',
    scope: {
        url: '@',
        nombre: '=',
        archivo: '=',
        nover: '='
    },
    template:
            '<i ng-click="verDocumento()" ng-hide="isVacio()" class="mi-view fa fa-eye"></i><span> {{nover?"":nombre}}</span>'
    ,
    link: function(scope,elem,attrs){
        
        if( scope.archivo instanceof MyFile)
            scope.nombre = scope.archivo.name;
        else if(typeof(scope.archivo) === "string"){
            scope.nombre = scope.archivo;
        }else{
            console.log("nombre archivo "+scope.nombre);
        }
        
        
        scope.verDocumento = function(){
            if( scope.archivo instanceof MyFile )
                verDocumento(scope.archivo.buildDataURL());
            else{
                if(scope.url)
                    verDocumento("/SIGESMED/"+scope.url+scope.nombre);
                else
                    verDocumento("/SIGESMED/"+scope.nombre);
            }
                
        };
        scope.isVacio = function(){
            return scope.nombre==null || scope.nombre==="";
        };
      }
    };
}]);


app.directive('viewFileIcono',[ function () {
return {
    restrict: 'E',
    scope: {
        url: '@',
        nombre: '=',
        archivo: '='
    },
    template:
    '<i ng-click="verDocumento()" ng-hide="isVacio()" class="mi-view fa fa-eye"></i>'
    ,
    link: function(scope,elem,attrs){
        
        if( scope.archivo instanceof MyFile)
            scope.nombre = scope.archivo.name;
        else if(typeof(scope.archivo) === "string"){
            scope.nombre = scope.archivo;
        }else{
            console.log("nombre archivo "+scope.nombre);
        }
        
        
        scope.verDocumento = function(){
            if( scope.archivo instanceof MyFile )
                verDocumento(scope.archivo.buildDataURL());
            else{
                if(scope.url)
                    verDocumento("/SIGESMED/"+scope.url+scope.nombre);
                else
                    verDocumento("/SIGESMED/"+scope.nombre);
            }
                
        };
        scope.isVacio = function(){
            return scope.nombre==null || scope.nombre==="";
        };
      }
    };
}]);
/*funciones para crear un objeto tipo archivo*/
/*funcion que lee un input file para crear un objecto MyFile*/
function crearMyFile(file){    
    var miArchivo = new MyFile(file.name);
    miArchivo.size=file.size;

    var reader = new FileReader();
    //implementando la funcion onload
    reader.onload = function(){
        miArchivo.parseDataURL(reader.result );
    };

    //leendo la imagen
    reader.readAsDataURL(file);
    
    return miArchivo;
};
//varios archivos
function crearArrayMyFile(files){
    
    var listaArchivos = [];

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {
        
        var file = f; //un solo archivo
        var miArchivo = new MyFile(file.name);

        var reader = new FileReader();
        //implementando la funcion onload
        reader.onload = function(){
            miArchivo.parseDataURL(reader.result );
        };

        //leendo la imagen
        reader.readAsDataURL(file);

        listaArchivos.push( miArchivo );
      
    }
    return listaArchivos;
    
};
/**/
app.directive('sectionTable',['$log',function ($log){
    return{
        templateUrl: 'section_table.html',
        restrict:'E',
        replace: true,
        scope:{
            model: '='
        },
        link: function($scope,$element,$attrs){
            initData();
            $scope.current;
            var levels = new Array($scope.model.titles.length - 1);
            function initData(){
                var titles = $scope.model.titles;
                var data = $scope.model.data;
                if(angular.isArray(titles)){
                    var index = 0;
                    angular.forEach(titles,function(value,key){
                        if(!angular.isObject(value)){
                            titles[key] = {id:index++,title:value};
                        }
                    });
                }
            }
            $scope.showSecondLevel = function(data){
                var index = _.findIndex($scope.model.data,function(o){
                    return data.nom === o.nom;
                })
                $scope.current = $scope.model.data[index];
               levels[0]= $scope.nextArray($scope.current);
                //borrar los demas
                for(var i = 1 ; i < levels.length; i++){
                    levels[i] = [];
                }
            }
            $scope.showContent = function(id){
                return levels[id];
            }
            $scope.nextArray = function(data){
                if(angular.isObject(data)){
                    for(var a in data)
                        if(angular.isArray(data[a])) {
                            return data[a];
                        }
                }else
                    return null;
            }
            $scope.showNext = function(data,index){
                if(index < levels.length - 1){
                    levels[index + 1] = $scope.nextArray(data);
                }
                if(data.selec != undefined)
                    data.selec = !data.selec;
                else
                    data.selec = true;
                //$log.log('model data',$scope.model.data);
            }
        }
    }
}]);
/*Componente para subir un archivo*/
app.directive('inputFileCsv',[ function () {
return {
    restrict: 'E',   
    scope: {
        archivo: '=',
        nombre: '=',
        header: '=?',
        verificar: '=?'
            
    },
    template:
            '<input type="file" class="mi-input col-xs-2 col-sm-2" onchange="angular.element(this).scope().cambiarCSV(this)" ></input><div class=" col-xs-10 col-sm-10"><label>{{nombre}}</label></div>'
    ,
    link: function(scope,elem,attrs){
          scope.cambiarCSV = function(input){
              
            scope.$apply(function () {
                if( input.files[0].type.match(/text\/csv/) || input.files[0].type.match(/vnd\.ms-excel/) ){
                        
                scope.nombre = input.files[0].name;
                scope.archivo = upload(input.files[0],scope.header,scope.verificar);                              
            }
            else{
                alert("SELECCIONE UN ARCHIVO CSV");
            }
            });
              
              /*input.files[0] = {};//reiniciando el archivo*/
          };
      }
    };
}]);
           

/*funciones para crear un objeto tipo archivo*/
/*funcion que lee un input file para crear un objecto MyFile*/
function upload(file,flag,datos){   
           
            var json = new MyFileJSON();

            var reader = new FileReader();
            reader.onload = function() {           
            json.parseCSV(reader.result,flag,datos );           
                       
            };            
            reader.readAsText(file,"ISO-8859-1");     
       
    return json;
};