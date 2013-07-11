package rodri;

import java.io.*;
import java.util.*;


public class ServidorWeb {

	static String fichero;
	static String peticion;




	public static void  main(String []args) throws IOException{



		String peticion=leerFichero();
		//Fichero=new File("C:\\");


		//en numFich estara el nombre del fichero, busca en el direc d trabajo el fichero y lo abre



		//leo la primera linea de la peticion(GET/XELU.JPG...)

		//EntradaTexto.close();  ?

		System.out.println(peticion);//prueba para ver si leyo bien

		StringTokenizer tokens=new StringTokenizer(peticion);
		String comando=tokens.nextToken(); // saco el primer comando para ver si es ubn GET



		if(comando.equals("GET")){ // cojo el sig token para procesar fichero

			comando=tokens.nextToken();

			if(comando.endsWith("/")){
				comando+="index.html";
			}




			comando=comando.substring(1);

			File fil=new File(comando); // ?????????????????????
			if(!fil.exists())  //NUNCA ENTRA AQUI...........
				cabecera_MIME("404 Fle Not Found",comando);//le digo ke no existe...


			else{
				cabecera_MIME("200 OK",comando);
				if(comando.endsWith("html")|| comando.endsWith("htm")){//Solo en ese caso escribo el fichero
					ficherotextual(comando);

				}
			}




		}
		else{

			System.out.println("no implementado");
		}

	}


	static String leerFichero() throws IOException{ //leemos el fichero

		FileReader fr=null;
		BufferedReader EntradaTexto=null;
		fr=new FileReader("contenidos.txt");
		EntradaTexto=new BufferedReader(fr);
		String peticion=EntradaTexto.readLine();
		return peticion;

	}



	static void cabecera_MIME(String resp,String nomfich){

		try{
			System.out.println(resp+"HTTP/1.0");
			Date now=new Date();
			System.out.println("Date: " + now);
			System.out.println("Server:ld-http-get");

			if(nomfich.endsWith(".html") || nomfich.endsWith("htm")){
				System.out.println("content.type :text html\n");
			}
			else{

				System.out.println("image/jpeg");
				System.out.println("text/plain");
			}
		}catch(Exception e) {
			System.out.println("Excepcion de cabecera_MIME");
		}
	}


	static void ficherotextual(String numFich)
	{
		try{
			FileReader fich=new FileReader(numFich);
			BufferedReader fichBR =new BufferedReader(fich);
			String linea=fichBR.readLine(); //LEO LINEA A LINEA HASTA QUE ACABO

			while(linea!=null){
				System.out.println(linea);
				linea=fichBR.readLine();
			}
		}catch(Exception e) {System.out.println("except");}

	}
}


