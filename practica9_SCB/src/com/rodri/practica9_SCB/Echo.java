package com.rodri.practica9_SCB;
import java.io.*;
import java.net.*;


//idle=0 y waitack=1 tenemos una variable state que compararemos con 0 o 1 para ver que hacemos
public class Echo {
static byte Idle=0, WaitAck=1;

public static void main (String args[]) {
	boolean salir=false;//El cliente estara conectado de continuo. 
	//Al pulsar un punto la variable salir se pondr� a true y saldremos.
	String host = "localhost";
	int remotePort=7; //Puerto inicial del servidor
	byte state=0;
	int numlin=1;
	if (args.length > 0) { host=args[0]; }
	//Podr�amos pasarle como argumentos el host.
	//Como no le metemos, toma localhost
	try {
		InetAddress a = InetAddress.getByName(host);//Direccion servidor y puerto en el que esta
		//para utilizar datagramas
		DatagramSocket theSocket = new DatagramSocket();//socket para enviar y recibir
		DatagramPacket dpout= null;//Definimos variable datagram packet
		BufferedReader lin= new BufferedReader (new InputStreamReader(System.in));
		//Leemos por teclado la informacion que enviaremos
		while (!salir) {
			if (state==Idle) {
				System.out.println("Introduzca una l�nea (acabar con '.'):");
				String line= lin.readLine();//Leemos la linea
				if (line.equals(".")) salir=true; //Comprobamos si acaba en punto.
				byte[] dataenv={(byte)(numlin%2)};//Construimos un array de tipo byte donde 
				//en la primera posicion metemos el numero de linea.(ser� 0 o 1)
				line= (new String (dataenv))+line;//Concatenamos el mensaje que hemos escrito al 0/1 del byte
				dataenv= line.getBytes();//Convertimos a byte
				dpout= new DatagramPacket(dataenv, dataenv.length , a, remotePort);
				//Creamos un datagrama con a=localhost y remoteprot el puerto donde tengo que enviar el datagrama
				theSocket.send(dpout); // envia un datagrama al servidor y
				state=WaitAck; // cambia de estado para esperar asentimiento(1)
			} else { //waitack state
//a partir de aki mirar esto
		try {//entra aqui cuando esperamos, ya se ha enviado el datagrama
			theSocket.setSoTimeout(2000); // Genera una excepci�n tras 2 seg. de espera por un datagrama de asentimiento
			DatagramPacket dpin= new DatagramPacket(new byte[256], 256);//Creamos datagrama para recibir informacion
			//siempre creamos datagrama para recibir o enviar
			theSocket.receive(dpin);//Recibimos
			//dpin es el datagram packet que recibe del servidor
			//dport el datagram que utiliza para mandar al servidor
			//la primera vez el 7 luego coge el puerto de la hebra y modifica para mandar por ahi
			remotePort= dpin.getPort(); // Puerto de la hebra del servidor que atiende a ese cliente
			dpout.setPort(remotePort); //Modificamos el remote port por el de la hebra.
			byte[] datarec=dpin.getData();
			if (datarec[0]==(byte)((numlin+1)%2)){ //El bit que me envia ha de ser igual al siguiente que espero.Si es as� la transmisi�n ha sido valida.
				System.out.println("Recibido el ACK esperado: "+datarec[0]);
				String s = new String(datarec, 1, dpin.getLength()-1);//Cogemos el mensaje quitando el numero primero
				System.out.println("Eco "+numlin+" de "+dpin.getAddress()+" : " + s);
				numlin++;
				state=Idle;
			} else { // Ack no esperado.Si lo que me envia el xervidor no es correcto
					System.out.println("Recibido ACK NO esperado: "+
					datarec[0]+" - retranmision");
					theSocket.send(dpout);//vuelve a enviarle el paquete hasta que coincida respuesta correcta
			}
			} catch (SocketTimeoutException e){ //no llega asentimiento en 2 seg.
			System.out.println("Timeout - Retranmision");
			theSocket.send(dpout);//Volvemos a enviar el mismo paquete
			}
	
	}
	} //while
	theSocket.close();
	} catch (UnknownHostException e) {System.err.println(e);
} catch (IOException e) {} } }
