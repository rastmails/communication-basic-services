package com.rodri.practica4_SCB;

import java.util.Arrays;

public enum Cadena {
	DIR("DIR"),
	PUT("PUT"),
	GET("GET"),
	OK("OK"),
	ERROR("ERROR"),
	READY("READY"),
	FIN("#FIN#");
	
	private String nombre;
	
    private Cadena(final String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Devuelve el enum deseado o lanza una excepcion
     * @param nombreComando - Cadena de texto con la representacion del comando que se quiere
     * @return Comando
     */
    public static Cadena getEnum(String nombreCadena){
    	nombreCadena = nombreCadena.toUpperCase();
    	// si la cadena es "#FIN#" devuelve Comando.FIN.
        if(FIN.toString().equals(nombreCadena)){
            return FIN;
        }
        // si la cadena corresponde al valor de alguno de los enum que quedan
        // se devuelve ese enum.
        else if(Arrays.asList(Cadena.values()).contains(Cadena.valueOf(nombreCadena))){
        	return Cadena.valueOf(nombreCadena);
        }
        // si no se ha encontrado ninguna correspondencia debe
        // estar mal el nombre que se le pasa por parametro.
        throw new IllegalArgumentException("No hay ningun enum definido para esta cadena: "+nombreCadena);
    }
    
	@Override
	public String toString(){
		return nombre;
	}
}
