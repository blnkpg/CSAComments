package uebung2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class Aufgabe4 {

	/**
	 * Gibt die Index.html des übergebenen InetAddress als string zurück
	 * 
	 * @param address
	 * @return
	 */
	public static String run(InetAddress address ) {
		int port = 80;
		String result ="";
		
		try {
			Socket sock = new Socket(address, port);	
			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();
			String standardDatei = "/index.html";
			String befehl = "GET " + standardDatei + " HTTP/1.0" + "\r\n\r\n";
			//"\r\n\r\n" sind zeilenumbrüche...
			out.write(befehl.getBytes());
			
			int length;
			byte[] streamedBytes = new byte[100];
			while ((length = in.read(streamedBytes)) != -1 ) {
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				ps.write(streamedBytes, 0, length);
				result += baos.toString();

				System.out.write(streamedBytes, 0, length);
			
			}			
			in.close();
			out.close();
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
