app.service("UserService",function(SendRequest){
	
	this.getAll = function(url,request){
		return SendRequest.noAuthenticatedRequest('GET',url,null);
	};
	
	this.add = function(url,request){
		return SendRequest.noAuthenticatedRequest('POST',url,request);
	}
	
	this.update = function(url,request){
		return SendRequest.noAuthenticatedRequest('PUT',url,request)
	}
	
	this.getTasks = function(url,request){
		return SendRequest.noAuthenticatedRequest('GET',url,request)
	}
	
});

/*el atributo ->i<- esta reservado para indicar la posicion del elemento en el array*/
/*Funcion que añade la posicion a los elementos de un array*/
function iniciarPosiciones(miArray){
    miArray.forEach(function(item,index){
        item.i = index;
    });
};
/*Funcion que inserta elemento en la posicion final*/
function insertarElemento(miArray, elemento){
    elemento.i = miArray.length;
    miArray.push(elemento);
};

/*Funcion que elimina un elemento de un array y reinicia las posiciones de las elementos restantes*/
function eliminarElemento(miArray, posElemento){
    //eliminando elemento
    miArray.splice( posElemento ,1);
    //reiniciando las posiciones de los elementos restantes al eliminado
    for (var i = posElemento,l = miArray.length; i < l; i++)
        miArray[i].i = i;
};


