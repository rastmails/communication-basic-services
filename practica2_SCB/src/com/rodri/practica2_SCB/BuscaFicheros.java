package com.rodri.practica2_SCB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Rodrigo Alvarez Echarri.
 * Date: 02/10/12
 * Time: 11:28 AM
 * 
 */

public class BuscaFicheros {
	
	public String file;
	public String searched;
	public String type;
	 
	LinkedList<String> result;
	//Inicializo las variables
	public BuscaFicheros ( String name ,String f, String tipoArchivo){
		file	 = f;
		searched = name;
		type 	 = tipoArchivo;
		
	}
	
	public void SearchName(){
		try{
			int num		= 0;
			File list 	= new File(file);
			File[] lists= list.listFiles();
//			ArrayList<String> arreglo = new ArrayList<String>();
//	        filtrarYObtenerArchivos(arreglo,list,type);
			result 		= new LinkedList<String>();
			
			//Recorro todo el archivo, mirando en todos los ficheros.
			for(int i=0; i<lists.length; i++){
			//for(int i=0; i<arreglo.size(); i++){
				if(lists[i].getName().indexOf(".txt")!=-1){
					num++;
					//System.out.println(i+lists[i].getName()+num);
					Busqueda s = new Busqueda(lists[i], this, result);
					s.run();					
				}
			}while (num>result.size()){}
			FileWriter print 	= new FileWriter("salida.txt");
			BufferedWriter bw	= new BufferedWriter(print);
			while (!result.isEmpty()){
				synchronized(result){
					bw.write(result.remove(0));
					
					
				}
			}
			bw.close();
			FileReader listRead 	= new FileReader("salida.txt");
			BufferedReader lists2	= new BufferedReader(listRead);
			String line2			= lists2.readLine();
			while(line2!=null){
				System.out.println(line2);
				line2 = lists2.readLine();
			}
		}catch(IOException e){
			System.out.println(e);
		}
	}
	void recibeFileInfo(int line, String nomfich, String linea){
		//System.out.println(line+nomfich+"\r\n"+linea);
	}
	
	public String leer(String nombre)
	//El parametro nombre indica el nombre del archivo por ejemplo "prueba.txt"
	{
	try{
	File f;
	FileReader lectorArchivo;
	//Creamos el objeto del archivo que vamos a leer
	f = new File(nombre);
	//Creamos el objeto FileReader que abrira el flujo(Stream) de datos para realizar la lectura
	lectorArchivo = new FileReader(f);
	//Creamos un lector en buffer para recopilar datos a travez del flujo "lectorArchivo" que hemos creado
	BufferedReader br = new BufferedReader(lectorArchivo);
	String l="";
	//Esta variable "l" la utilizamos para guardar mas adelante toda la lectura del archivo
	String aux="";/*variable auxiliar*/
	while(true)
	//este ciclo while se usa para repetir el proceso de lectura, ya que se lee solo 1 linea de texto a la vez
	{
	aux=br.readLine();
	//leemos una linea de texto y la guardamos en la variable auxiliar
	if(aux!=null)
	l=l+aux+"\n";
	/*si la variable aux tiene datos se va acumulando en la variable l,
	* en caso de ser nula quiere decir que ya nos hemos leido todo
	* el archivo de texto*/
	 
	else
	break;
	}
	br.close();
	lectorArchivo.close();
	return l;
	}catch(IOException e){
	System.out.println("Error:"+e.getMessage());
	}
	return null;
	}



//	public static void main(String args[]) {
//		BuscaFicheros bf = new BuscaFicheros(args[0], args[1], args[2]);
//		bf.SearchName();
//	} // main

	
	//EXTRA: Para poder buscar mas extensiones que no solo .txt
//	public void filtrarYObtenerArchivos(ArrayList<String> archivosFiltrados,File ruta, String tipoArchivo){
//        File[] ficheros = ruta.listFiles();
//
//        for (int x=0;x<ficheros.length;x++){
//            //ficheros[x]=ficheros[x].getPath();
//                String [] resultado= ficheros[x].list( new Filtro(tipoArchivo));
//
//                if (ficheros[x].isDirectory()){
//                    for(int j=0;j<resultado.length;j++){
//                        System.out.println(ficheros[x].getPath()+"\\"+resultado[j]);
//                        archivosFiltrados.add(ficheros[x].getPath()+"\\"+resultado[j]);
//                        //System.out.println(ficheros[x].getPath());
//                    }
//                    //obtener el paht
//
//                filtrarYObtenerArchivos(archivosFiltrados, ficheros[x], tipoArchivo);
//               }
//        }
//
//    }
//	
//	 private ArrayList<String> obtenerArchivosConCoincidencias(ArrayList<String> arreglo, String criterio) {
//		 
//	 }
	
	
	//EXTRA: Para en caso de usar linux, pero sin hilos. Usando la busqueda nativa.
	public String utilizandoLinux(String criterio, String directorio, String tipoArchivo){
        String line = null;
        String comando="grep -in \"" + criterio + "\" */*"+ tipoArchivo;
        File wd = new File(directorio);
        StringBuilder salida = new StringBuilder();
        try{
            //Process p = Runtime.getRuntime().exec("grep -in \"Menu_ordna\" */*.4gl",null, wd);
            Process p = Runtime.getRuntime().exec(comando,null, wd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            System.out.println("Recibiendo lo de la consola. . . . ");
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                salida.append(line);
                salida.append(System.getProperty("line.separator"));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Termino el proceso...");
        salida.append("Termino la busqueda...");
        return salida.toString();
    }
}
