package uebung2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TelnetServer implements Runnable{
	
	//Gibts schon diese klasse Klasse. Kann Verbindungen entgegennehmen, aber nicht selber Verbindungen aufbauen
	private ServerSocket server = null;

	/**
	 * Stellt den ServerSocket zur verfügung und startet die clientSockets
	 */
	@Override
	public void run() {
		try {
			//Bitte nimm Verbindungen auf port 23 entgegen
			server = new ServerSocket(23);
			//workaround, weil Eclipse zu blöd war die Klasse zu finden
			Class.forName("uebung2.HtmlLogger");
			//			(siehe htmllogger)
			HtmlLogger log = new HtmlLogger();
			
			//solange programm läuft horche auf port 23
			while(true){
				//wenn über port 23 angesprochen, verbindung wird angenommen und in ClientSocket gespeichert
				Socket clientSocket = server.accept();
				System.out.println("client socket initialized");
				//siehe htmllogger.write
				log.write(clientSocket.toString());
				//Gibt vor wie mit Client 
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
