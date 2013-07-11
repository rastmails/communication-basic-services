package rodri;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class busqueda {
	
	public static void  main(String []args) throws IOException{
		String peticion=leerFichero();
		
		
		
		
		
	}
	static String leerFichero() throws IOException{ //leemos el fichero

		FileReader fr=null;
		BufferedReader EntradaTexto=null;
		fr=new FileReader("contenidos.txt");
		EntradaTexto=new BufferedReader(fr);
		String peticion=EntradaTexto.readLine();
		return peticion;

	}

}
