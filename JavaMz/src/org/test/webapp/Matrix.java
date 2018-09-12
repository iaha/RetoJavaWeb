package org.test.webapp;

import java.util.Arrays;

public class Matrix {
	private String size = null;
	private String data = null;
	private String operation = null;
	//private int alto = 0;
	private int ancho = 0;
	private char[] contenido = null;
	private String resultado = null;

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
		String[] dimentions = size.split("-");
		//this.alto = Integer.parseInt(dimentions[0]);
		this.ancho = Integer.parseInt(dimentions[1]);
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
		this.contenido = data.toCharArray();
	}
	public String getOperation() {
		if(operation.equalsIgnoreCase("ocurrencias"))
			return "Consultar ocurrencias";
		else if(operation.equalsIgnoreCase("repetidos"))
			return "Eliminar repetidos";
		else
			return "Ordenar ascendentemente";
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
/*
	public int getAlto() {
		return alto;
	}
	public int getAncho() {
		return ancho;
	}
	public int getTotalSize() {
		return alto * ancho;
	}
	public char[] getContenido() {
		return contenido;
	}*/
	public void setResultado(String result) {
		this.resultado = result;
	}
	public String getResultado() {
		return resultado;
	}
	public String arrayToString(char[] array) {
		String result = "";
		for(char c: array) result += c;
		return result;
	}
	public String printToHTML(String srt) {
		char[] array = srt.toCharArray();
		String result = "<table class=\"matrix\">";
		String row = "";
		for(int i = 0; i < array.length; i++) {
			if((i % this.ancho) == 0) {
				result += "<tr>" + row + "</tr>";
				row = "<td class=\"matrix\">" + array[i] + "</td>";
			} else row += "<td class=\"matrix\">" + array[i] + "</td>";
		}
		return result + "<tr>" + row + "</tr></table>";
	}

	public String sortAsc() {
		char[] array = this.contenido;
		Arrays.sort(array); // sorted in ascending order
		return arrayToString(array);
	}

	public String removeDuplicates() {
		char[] array = this.contenido;
	    String _array = "";
	    for(int i = 0; i < array.length; i++) {
	        if(_array.indexOf(array[i]) == -1) // check if a char already exist
	            _array += array[i];
            else _array += "*";
	    }
	    return _array;
	}

	public String countOcurrency() {
		String str = data;
		String result = "";
        int count[] = new int[256]; 
        int len = str.length();
  
        // Initialize count array index 
        for (int i = 0; i < len; i++) 
            count[str.charAt(i)]++; 
  
        // Create an array of given String size 
        char ch[] = new char[str.length()]; 
        for (int i = 0; i < len; i++) { 
            ch[i] = str.charAt(i); 
            int find = 0; 
            for (int j = 0; j <= i; j++) { 
                if (str.charAt(i) == ch[j])  
                    find++;                 
            } 
  
            if (find == 1)
            	result += str.charAt(i) + "(" + count[str.charAt(i)] + "),";
        }
        return result.substring(0, result.length() - 1);
    }
}
