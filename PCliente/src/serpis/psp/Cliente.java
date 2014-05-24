package serpis.psp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


//Se debe crear una conexion a un servidor http mediante el uso de la clase socket
//y pedir que nos envie un recurso mediante el envio del mensaje "GET /index.html HTTP/1.0".
//El programa pedirá al usuario una dirección web para establecer la conexión,
//enviará el mensaje y recibirá la respuesta del servidor. Esta será almacenada
//en un archivo File de texto.
//Esta acción se repetira un numero determinado de veces en este orden mediante el uso 
//de la clase Thread y la implementación de el interface runnable.


public class Cliente {
	private static String SERVER_IP = "";
	private static final int SERVER_PORT = 80;
	private static Scanner teclado = new Scanner(System.in);
	private static Socket socket;
	
	public static void main(String [] args) throws IOException{

		new Thread( new Runnable() {
		@Override
		public void run(){
			String file = "/home/ramon/resultado.txt";
			for (int i = 3;i >=0;i--){

			System.out.println("Introduce IP o direccion web:");
			
			SERVER_IP = teclado.next();
			
			try {
				socket = new Socket(SERVER_IP,SERVER_PORT);
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
				Scanner scanner = new Scanner(socket.getInputStream());
				FileWriter fichero = new FileWriter(file,true);
				sendClient(socket,printWriter);
				writeFile(fichero,file,scanner);
				
				System.out.printf("Numero de thread %s, servidor: %s puerto: %s \n",i,SERVER_IP,SERVER_PORT );
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		}).start();
		//fichero.close();
	}
	
	private static void sendClient(Socket socket,PrintWriter printWriter) throws InterruptedException{
		String peticion = "GET /index.html HTTP/1.0 \r\n"+
						  "\r\n";
		printWriter.print(peticion);
		printWriter.flush();
		Thread.sleep(1000);
		System.out.println("Peticion enviada: "+ peticion);
	}
	
	private static void writeFile(FileWriter fichero,String file,Scanner scanner) throws IOException{
		try{
			if(file.isEmpty()){
				 
				 while (true){
					 String linea = scanner.nextLine();
					 System.out.println(linea);
					 fichero.write(linea);
				 }
				 }else{
					 while(true){
					 String lineaBlanco = "\r\n \r\n";
					 String linea = scanner.nextLine();
					 System.out.println(linea);
					 fichero.write(lineaBlanco);
					 fichero.write(linea);
					 }
				 }
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
