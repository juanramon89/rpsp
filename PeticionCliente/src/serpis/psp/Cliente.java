package serpis.psp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Cliente {
	
	private static final String SERVER_IP = "173.194.40.159";
	private static final int SERVER_PORT = 80;
	
	public static void main(String [] args) throws UnknownHostException, IOException, InterruptedException{
		
		System.out.println("Cliente envia peticion:");
		Socket socket = new Socket(SERVER_IP,SERVER_PORT);
		
		String peticion = "GET /index.html HTTP/1.0";
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		//Scanner scanner = new Scanner(socket.getInputStream());
		
		
		printWriter.print(peticion);
		printWriter.flush();
		Thread.sleep(1000);
		System.out.println("Peticion enviada: "+ peticion);
		
		 DataInputStream entrada;
		 try {
		 entrada = new DataInputStream( socket.getInputStream());
		 
		 while (entrada.read() != -1){
			 String linea = entrada.readLine();
			 System.out.println(linea);
		 }
		 
		 } catch( IOException e ) {
		 System.out.println( e );
		 }
		
	}

}
