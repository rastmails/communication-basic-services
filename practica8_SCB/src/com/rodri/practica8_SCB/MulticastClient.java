package com.rodri.practica8_SCB;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {
	static MulticastSocket s;

	public static void main (String args[]) throws IOException {
		byte[] recibido;
		InetAddress group = InetAddress.getByName("228.6.6.6");
		s = new MulticastSocket(7777);
		s.joinGroup(group);
		while(true) {
			recibeString();
		}
	} 

	/**
	 * Recibe una cadena de texto.
	 */
	private static void recibeString() {
		byte[] buffer = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		try {
			s.receive(dp);

			System.out.println(" recibido :" + new String(dp.getData()));
		} catch (IOException ex) { 	System.err.println(ex);} 
	}
}