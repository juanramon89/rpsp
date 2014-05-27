package serpis.psp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMini {
	
	private static final int SERVER_PORT=2121;
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		
		Scanner teclado = new Scanner(System.in);
		System.out.printf("TcpClient SERVER_IP=%s port=%s\n", args[0], SERVER_PORT);
		Socket socket = new Socket(args[0],SERVER_PORT);
		FileOutputStream fileOutputStream = new FileOutputStream("/home/ramon/"+args[1]+".txt");
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		BufferedInputStream  bufferedInputStream = new BufferedInputStream(socket.getInputStream());
		byte [] buf = new byte[2048];
		
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		
		//Scanner scanner = new Scanner(socket.getInputStream());
	
		String peticion = "GET "+args[1]+"\n";
		printWriter.print(peticion);
		printWriter.flush();
		//Thread.sleep(1000);
		System.out.println("Peticion enviada: "+ peticion);
		
		
			bufferedInputStream.read(buf);
			bufferedOutputStream.write(buf);
		
		fileOutputStream.close();
		bufferedOutputStream.close();
		
	}

}
