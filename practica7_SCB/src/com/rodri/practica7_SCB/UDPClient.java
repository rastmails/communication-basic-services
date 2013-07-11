package com.rodri.practica7_SCB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPClient {
	static DatagramSocket ds;


	public static void main (String args[]) throws Exception {
		// consigue los parametros
		String host = "localhost";
		if(args.length>0) host = args[0];
		InetAddress address = InetAddress.getByName(host);
		int serverPort = 1255;
		if(args.length>1) serverPort = Integer.parseInt(args[1]);
		try {
			ds  = new DatagramSocket();
		} catch (SocketException e) { e.printStackTrace(); }
		// itera en un bucle infinito lanzando hebras de echo
		// hasta que se escribe un punto "." para salir
		boolean iterar = true;
		while (iterar) {
			String linea = getTeclado();
			new EnviaThread(address, serverPort, linea, ds).start();
			new RecibeThread(address, serverPort ,ds).start();
			if(linea.equals(".")) iterar = false;
		}
	}

	/**
	 * Obtiene una string de terminal
	 */
	private static String getTeclado() {
		String linea = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print(">>");
			linea = teclado.readLine();
			System.out.println();
		} catch (IOException e) { e.printStackTrace(); 	}
		return linea;
	}
}




/*
 * Realiza la operación de "echo". Con los parámetros que se le
 *  pasa intenta enviar y recibir una cadea.
 */
class EnviaThread extends Thread{
	private InetAddress address;
	private int port;
	private DatagramSocket ds;
	private String mensaje;
	public EnviaThread(InetAddress address, int port, String mensaje, DatagramSocket ds) {
		super();
		this.address	= address;
		this.port 		= port;
		this.mensaje	= mensaje;
		this.ds  = ds;
	}

	@Override
	public void run() {
		enviaString();
		super.run();
	}

	/**
	 * Envia una cadena de texto.
	 */
	private void enviaString() {
		byte[] data = this.mensaje.getBytes();
		System.out.println();
		System.out.println(" enviado en string	:" + mensaje);

		DatagramPacket output = new DatagramPacket(data, data.length, address, port);
		try {
			ds.send(output);
		} catch (IOException e) {e.printStackTrace();}
	}

}

/*
 * Realiza la operación de "echo". Con los parámetros que se le
 *  pasa intenta enviar y recibir una cadea.
 */
class RecibeThread extends Thread{
	private InetAddress address;
	private int port;
	private DatagramSocket ds;
	public RecibeThread(InetAddress address, int port, DatagramSocket ds) {
		super();
		this.address	= address;
		this.port 		= port;
		this.ds			= ds;
	}

	@Override
	public void run() {
		recibeString();
		super.run();
	}

	/**
	 * Recibe una cadena de texto.
	 */
	private void recibeString() {
		byte[] buffer = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		try {
			ds.receive(dp);
			System.out.println(" recibido en string	:" + new String(dp.getData()));
		} catch (IOException ex) { 	System.out.println(ex); }
	}
}