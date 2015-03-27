package uebung2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
	private Socket clientSocket = null;
	private Menu menu = null;
	
	/**
	 * Der Konstruktor brauch den Client Socket damit er den zu behandelnden Client identifizieren kann
	 * @param clientSocket
	 */
	//übergebe verbindung zum client. 
	public ClientHandler(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	/**
	 * Schreibt ddie Menüs und und leitet Benutzereingaben für die weitere verarbeitung weiter
	 */
	@SuppressWarnings("null")
	public void run(){
		try {
			//öffne buffered writer und reader.
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// gehe in Menü klasse 
			this.menu = new Menu(out , in);
			//kvariablen zum speichern des in- und output
			String input = "";
			String output = "";
			
			while (true) {
				//gebe auf Konsole des clients das menü aus
				out.println(menu.printMenu());
				//was er reinschreibt auf konsole speichere in input
				input = in.readLine();
				if (!input.equals("0")){
					//Führe aufgabe aufgabe aus menü aus
					output = menu.auswahl(input);
					//gib resultat zurück
	            	out.println(output);
	            	//schreib ausgabe die an benutzer gesendet wurde in html datei. 
	            	//Wir speichern das nochmal ab für nützlichen einsatz des webservers
	            	HtmlLogger.write(output);
	            }else{
	            	out.println("Bye Bye");
					in.close();
					out.close();
					clientSocket.close();
	            	break;
	            }

	        }
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
