package serpis.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UpdCliente {
	
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 12345;

	private static final int MAX_PACKET_SIZE = 2048;
	
	public static void main(String [] args) throws IOException{
		System.out.println("UdpClient");
		DatagramSocket datagramSocket = new DatagramSocket();
		Date fecha = new Date();
		
		String outMessage = "Hora de envio del mensaje: " + fecha.toLocaleString();
		System.out.println("outMessage   =" + outMessage);
		byte[] outBuf = outMessage.getBytes();
		int outLength = outBuf.length;
		InetAddress serverInetAddress = InetAddress.getByName( SERVER_IP );
		DatagramPacket outDatagramPacket = new DatagramPacket(outBuf, outLength, serverInetAddress, SERVER_PORT);
		datagramSocket.send(outDatagramPacket);

		/*
		byte[] inBuf = new byte[ MAX_PACKET_SIZE ];
		int inLength = inBuf.length;
		DatagramPacket inDatagramPacket = new DatagramPacket(inBuf, inLength);
		datagramSocket.receive(inDatagramPacket); //aquí espera hasta recibir
		String inMessage = new String(inBuf, 0, inDatagramPacket.getLength());
		System.out.println("inMessage    =" + inMessage);
		
		datagramSocket.close();
		System.out.println("UdpClient end.");
		*/
		
	}

}
