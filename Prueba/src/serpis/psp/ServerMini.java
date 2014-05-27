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
		System.out.printf("TcpServer socket.getInetAddress()=%s socket.getPort()=%s\n", socket.getInetAddress(), socket.getPort());
		new Thread( new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					
					Scanner scanner = new Scanner(socket.getInputStream());
					FileInputStream fileInputStream = new FileInputStream(fichero);
					//FileOutputStream fileOutputStream = new FileOutputStream(fichero);
					BufferedInputStream  bufferedInputStream = new BufferedInputStream(fileInputStream);
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
					byte[] buf = new byte[2048];
					String lineIn = scanner.nextLine();
					System.out.printf("TcpServer Recibido='%s'\n", lineIn);
					//lineIn.startWith("GET");
					String archivo = lineIn.substring(4);
					
					if(archivo == "ejemplo"){
						
						bufferedInputStream.read(buf, 0, 5000);
						bufferedOutputStream.write(buf);
						fileInputStream.close();
						bufferedOutputStream.close();
						
					}

					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
 	
		
}
