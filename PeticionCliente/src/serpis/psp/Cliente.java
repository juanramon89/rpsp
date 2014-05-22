package serpis.psp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	private static final String SERVER_IP = "173.194.41.248";
	private static final int SERVER_PORT = 80;
	
	public static void main(String [] args) throws UnknownHostException, IOException, InterruptedException{
		
		System.out.println("Cliente envia peticion:");
		Socket socket = new Socket(SERVER_IP,SERVER_PORT);
		
		String peticion = "GET /index.html HTTP/1.0 \r\n"+
							"\r\n";
		
		String file = "/home/mati/resultado.txt";
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		Scanner scanner = new Scanner(socket.getInputStream());
		FileWriter fichero = new FileWriter(file);
		
		printWriter.print(peticion);
		printWriter.flush();
		Thread.sleep(1000);
		System.out.println("Peticion enviada: "+ peticion);
		
		 try{
			 fichero = new FileWriter(file);
			 
			 while (true){
				 String linea = scanner.nextLine();
				 System.out.println(linea);
				 fichero.write(linea);
			 }
				 
		 }catch (Exception e){
			 e.printStackTrace();
		 }
		 fichero.close();
		socket.close();
	}

}
