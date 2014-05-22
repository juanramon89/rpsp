package serpis.psp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	private static String SERVER_IP = "";
	private static final int SERVER_PORT = 80;
	private static Scanner teclado = new Scanner(System.in);
	private static Socket socket;
	
	public static void main(String [] args){
		
		String peticion = "GET /index.html HTTP/1.0 \r\n"+
				"\r\n";
		
		new Thread( new Runnable() {
		@Override
		public void run(){
			System.out.println("Introduce IP o direccion web:");
			
			SERVER_IP = teclado.next();
			try {
				socket = new Socket(SERVER_IP,SERVER_PORT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}).start();
		
	}

}
