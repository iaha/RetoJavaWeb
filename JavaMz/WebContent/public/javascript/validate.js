/**
 * 
 */
const fields = [{
		"name" : "size",
		"selector" : $('input[name="size"]'),
		"size" : [2,5],
		"values" : [0,24],
		"type" : "string",
		"delimeter" : "-",
		"required" : true,
		"valid" : 0
	}, {
		"name" : "matrix_a",
		"selector" : $('input[name="matrix_a"]'),
		"size" : 0,
		"values" : "/[^1-9]+/",
		"type" : "string",
		"required" : true,
		"valid" : 0
	}, {
		"name" : "matrix_b",
		"selector" : $('input[name="matrix_b"]'),
		"size" : 0,
		"values" : "/[^a-zA-Z]+/",
		"type" : "string",
		"required" : true,
		"valid" : 0
	}, {
		"name" : "selection",
		"selector" : $('select[name="selection"]'),
		"type" : "options",
		"values" : ["numérica","letras"],
		"required" : true,
		"valid" : 0
	}, {
		"name" : "operation",
		"selector" : $('select[name="operation"]'),
		"type" : "options",
		"values" : ["ocurrencias","repetidos","ordenar"],
		"required" : true,
		"valid" : 0
	}];
const button = $('input.btn-success');
$('#message-box').hide();

// Manejo los cambios en los campos del formulario
fields[0].selector.change(() => changeHandler(0));
fields[1].selector.change(() => changeHandler(1));
fields[2].selector.change(() => changeHandler(2));
fields[3].selector.change(() => changeHandler(3));
fields[4].selector.change(() => changeHandler(4));


// Contiene el handler general para todos los fields
function changeHandler(i) {
	let codigo = 0;
	codigo = notEmpty(fields[i]);
	if(codigo > 0) codigo = fieldValidation(fields[i]);
	else showMessage(codigo);
	if(codigo > 0) {
		fields[i].valid = codigo;
		toggleDisabled(i, false);
		//alert('Change after all ' + i + ': ' + fields[i].valid);
	} else showMessage(codigo);
}

// Los campos deben ser del tipo adecuado!
function fieldValidation(field) {
	const val = field.selector.val();
	const condition = field.values;
    if (field.type == 'string') {
    	const size = field.size;
    	let dimen = val.split(field.delimeter);
    	if(field.name == 'size' && size[0] < val.length  && val.length <= size[1] &&
    			dimen[0] != "" && condition[0] < dimen[0] && dimen[0] < condition[1] &&
    			dimen[1] != "" && condition[0] < dimen[1] && dimen[1] < condition[1]) {
    		fields[1].size = dimen[0] * dimen[1];
    		fields[2].size = dimen[0] * dimen[1];
    		return 1;
    	} else if(size != val.length){// || !val.match(condition)) {
	    	return -4;
    	}
	} else if (field.type == 'options' && !condition.includes(val)) {
		return -4;
	}
   	return 1;
}

// Debe ingresar todos los campos obligarios!
function notEmpty(field) {
	if (field.selector.val() == "") return -3;
	else return 1;
}

// Permite cambiar el estado disabled de en campo y el siguiente
function toggleDisabled(init, bool) {
	if (init == 4) button.attr("disabled", bool);
	else if (init == 0)
		for(i = 1; i < 5; i++)
			fields[i].selector.attr("disabled", bool);
}

function check(i,e,value){
    const unicode = e.charCode ? e.charCode : e.keyCode;
    if(i < 2) { //entradas de solo numeros
	    //if(i == 0 && value.includes("-")) if( unicode == 45 ) return false;
	    if(unicode != 8) //backspace
	    	if(unicode < 48 || unicode > 57) // solo numeros [0-9]
	    		if(i == 0 && (unicode != 45 || unicode == 48)) return false;
	    		else if(i == 1) return false;
    } else //entradas de solo letras
    	if(unicode != 8)
	    	if((unicode < 65 || unicode > 90) && (unicode < 97 || unicode > 122)) 
	    		return false;
}

function checkLength(i, element){
    const fieldLength = element.value.length;
    let length = (i == 0) ? fields[i].size[1] : fields[i].size;
    if(fieldLength <= length) return true;
    else {
        let str = element.value;
        str = str.substring(0, str.length - 1);
        element.value = str;
    }
}


$('.validate-form').submit(() => {
    if($('#message').hasClass('alert-danger'))
    	event.preventDefault();
});


$('.validate-form').each(() => {
    $(this).focus(() => showMessage(-1));
});

function showMessage(type) {
	if (type == -1) {
		$('#message-box').removeClass('alert-validate');
		$('#message-box').hide();
	}
	else {
		$('#message-box').addClass('alert-validate');
		$('#message-box').show();
		switch (type) {
			case -3 :
				$('#message').text('Debe ingresar todos los campos obligarios!');
				break;
			case -4 :
				$('#message').text('Los campos deben ser del tipo adecuado!');
				break;
		}
	}
	
	
}


