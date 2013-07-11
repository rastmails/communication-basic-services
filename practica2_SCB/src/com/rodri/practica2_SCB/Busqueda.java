package com.rodri.practica2_SCB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Rodrigo Alvarez Echarri.
 * Date: 02/10/12
 * Time: 11:28 AM
 * 
 */

public class Busqueda extends Thread{
	
	private File file;
	private String name;
	private BuscaFicheros search;
	private int line;
	private String aux;
//	ArrayList<String> archivosAceptados = new ArrayList<String>();
//    String contenidoArchivo="";

	
	LinkedList<String> result;
	
	public Busqueda(File f, BuscaFicheros b, LinkedList<String> r){
		
		file 	= f;
		//System.out.println(f);
		search 	= b;
		//System.out.println(b);
		name 	= b.searched;
		//System.out.println(name);
		result	= r;
		//System.out.println(r);
	}
	
	public void run(){
		try{
			aux="";
			
			//Abro el fichero argumento.
			FileReader fileA = new FileReader (file);
			//Y cargo el Buffer para que el programa esté listo para leer
			BufferedReader Text = new BufferedReader (fileA);
			//Se leerá la primera linea.
			String TextLine = Text.readLine();
			System.out.println(TextLine);
			//La inicializo como linea 1.
			line = 1;
			//Recorro todo el archivo
			while (TextLine!=null){
				if (TextLine.indexOf(name)!=-1)
					//System.out.println("line"+line+" "+file.getName()+"\n"+TextLine+"\n\n");
					//Despues de comprobar que no está vacia, formateo la linea para la salida posterior.
					aux=aux+"line: "+line+"  "+file.getName()+"\r\n"+TextLine+"\r\n\r\n";
				//Incremento el valor una vez terminada de leer.
				line = line+1;
				//Vuelvo a leer para que la próxima entrada al bucle no sea vacia,
				// a no ser que la linea estuviera vacia.
				TextLine = Text.readLine();
			}
			synchronized(result){
				result.add(aux);
				aux="";
			}
			Text.close();
		}catch(Exception e){
			System.out.println("Error" + e);
		}
	}
	

}
