package serpis.psp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMini {
	
	public static final int SERVER_PORT = 2121;
	
	public static void main(String[] args) throws IOException{
		
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		final String fichero = "/home/ramon/workspace/Prueba/ejemplo.txt";
		
		final Socket socket = serverSocket.accept();

		new Thread( new Runnable(){
			@Override
			public void run() {
				System.out.printf("TcpServer socket.getInetAddress()=%s socket.getPort()=%s\n", socket.getInetAddress(), socket.getPort());
				try {
					
					Scanner scanner = new Scanner(socket.getInputStream());
					FileInputStream fileInputStream = new FileInputStream(fichero);
					BufferedInputStream  bufferedInputStream = new BufferedInputStream(fileInputStream);
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
					byte[] buf = new byte[25000];

					String lineIn = scanner.nextLine();
					System.out.printf("TcpServer Recibido='%s'\n", lineIn);
					//lineIn.startWith("GET");
					String archivo = lineIn.substring(4);
					System.out.println(archivo);
					
					if(archivo.toString().equals("ejemplo")){
						while(bufferedInputStream.read(buf) != -1){
			
						bufferedInputStream.read(buf);
						bufferedOutputStream.write(buf);
						System.out.println("Leido y Enviado DATOS");
						
						}
						socket.close();
					}
					fileInputStream.close();
					bufferedOutputStream.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}		
}
