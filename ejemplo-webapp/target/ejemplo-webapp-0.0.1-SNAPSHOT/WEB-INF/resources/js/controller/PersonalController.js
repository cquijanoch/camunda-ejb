app.controller("PersonalController",function($scope,UserService,modal,NgTableParams){
	$scope.personas = {};
	$scope.estados = [{id: 'A', title: "Activo"}, {id: 'E', title: "Cesado"}];
	var params = {count: 10};
    var setting = {counts: []};
    $scope.miTabla = new NgTableParams(params, setting);
    $scope.bancos= {
		    id:"",
    		nombre:""
	      };
    
    $scope.persona= {
		    idPersona:"",
		    codigo:"",
		    dni: "",
		    nombre: "",
		    apellidos: "",
		    direccion: "",
		    fecha_nacimiento: "",
		    existe:false
	      };
    $scope.trabajador= {
		    
		    idTrabajador: "",
		    cuspp: "",
		    tipo_pension: "",
		    carga_familiar: "",
		    cargo: "",
		    fecha_ingreso_planilla: "",
		    sueldo_bruto: "",
		    nro_cuenta_haberes: "",
		    banco_id: "",
		    nombreBanco: "",
		    estado:"",
		    existe:false
	      };
    
	
	$scope.iniciarDatos=function()
	{

		UserService.getAll("person/getPersonal").then(
				function(response){
					setting.dataset = response.response;
		            iniciarPosiciones(setting.dataset);
		            $scope.miTabla.settings(setting);
				},
				function(error){
					alert('Error');
				}
		);
		
		UserService.getAll("bancos/getAll").then(
				function(response){
					$scope.bancos = response.response;
		            
				},
				function(error){
					alert('Error');
				}
		);

	};
	
	
	$scope.prepararAgregar=function(){
		$scope.persona= {
			    idPersona:"",
			    codigo:"",
			    dni: "",
			    nombre: "",
			    apellidos: "",
			    direccion: "",
			    fecha_nacimiento: "",
			    existe:false
		      };
	    $scope.trabajador= {
			    
			    idTrabajador: "",
			    cuspp: "",
			    tipo_pension: "",
			    carga_familiar: "",
			    cargo: "",
			    fecha_ingreso_planilla: "",
			    sueldo_bruto: "",
			    nro_cuenta_haberes: "",
			    banco_id: "",
			    nombreBanco: "",
			    estado:"",
			    existe:false
		      };
		$("#modalNuevo").modal('show');
//		$("#modalRegistroHoras").modal('show');
	};
	
	$scope.cargarModalRegistroHoras=function(){
		$("#modalRegistroHoras").modal('show');
	};
	
	
	
});
	