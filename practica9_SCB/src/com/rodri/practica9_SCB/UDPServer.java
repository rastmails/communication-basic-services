package com.rodri.practica9_SCB;

import java.net.*; 

public class UDPServer {
	byte d[];
	static DatagramSocket data;
	public static void main (String args[]) {
		
		try {
			data = new DatagramSocket(7);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		System.out.println("Servidor UDP activo");
		while (true) {
			try {
				//Array de 1024 para recibir datos
				DatagramPacket dp=new DatagramPacket(new byte [1023],1023);			
				//Empaquetamos lo recibido
				data.receive(dp);
				new Threads(dp).start();						
			} catch (Exception e) {}
		}
	}
}

class Threads extends Thread {
	byte[] d;
	DatagramPacket data;
	int numerolinea=1;
	String mensaje;
	//constructor
	public Threads ( DatagramPacket p){	
		data=p;
	}
	//Cada hebra tira de su socket
	public void run(){
		try{
			boolean salir=false;
			DatagramSocket ds = new DatagramSocket();	
			while(!salir){
				System.out.println("Cliente conectado al puerto: "+ds.getLocalPort());
				d=data.getData();
				System.out.println("El cliente ha enviado: "+new String(d,1,data.getLength()-1));
				System.out.println("El numero de secuencia es: "+numerolinea);

				if(d[0]==numerolinea%2){
					numerolinea++;
				}
				d[0]=(byte)(numerolinea%2);
				//Constructor datagrama
				data=new DatagramPacket(data.getData(),data.getLength(),data.getAddress(),data.getPort());
				ds.send(data);
				//System.out.println(dp.getLength());
				d=new byte[1024];
				data=new DatagramPacket(d,d.length);			
				ds.receive(data);	//recibimos en dp
				mensaje=new String(d,0,data.getLength());
				String fin=mensaje.substring(1);
				if(fin.equals(".")){
					salir=true;
					System.out.println("El cliente se ha desconectado");
				}//Cierra la hebra con '.'
			}
			ds.close();
		} catch (Exception e) {}

	}}

