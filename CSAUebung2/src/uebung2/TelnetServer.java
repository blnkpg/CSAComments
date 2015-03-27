package uebung2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TelnetServer implements Runnable{
	
	private ServerSocket server = null;

	/**
	 * Stellt den ServerSocket zur verfügung und startet die clientSockets
	 */
	@Override
	public void run() {
		try {
			server = new ServerSocket(23);
			Class.forName("uebung2.HtmlLogger");
			HtmlLogger log = new HtmlLogger();
			
			
			while(true){
				Socket clientSocket = server.accept();
				System.out.println("client socket initialized");
				log.write(clientSocket.toString());
				ClientHandler handler = new ClientHandler(clientSocket);
				handler.run();				
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
