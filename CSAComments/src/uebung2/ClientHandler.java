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
	//�bergebe verbindung zum client. 
	public ClientHandler(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	/**
	 * Schreibt ddie Men�s und und leitet Benutzereingaben f�r die weitere verarbeitung weiter
	 */
	@SuppressWarnings("null")
	public void run(){
		try {
			//�ffne buffered writer und reader.
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// gehe in Men� klasse 
			this.menu = new Menu(out , in);
			//kvariablen zum speichern des in- und output
			String input = "";
			String output = "";
			
			while (true) {
				//gebe auf Konsole des clients das men� aus
				out.println(menu.printMenu());
				//was er reinschreibt auf konsole speichere in input
				input = in.readLine();
				if (!input.equals("0")){
					//F�hre aufgabe aufgabe aus men� aus
					output = menu.auswahl(input);
					//gib resultat zur�ck
	            	out.println(output);
	            	//schreib ausgabe die an benutzer gesendet wurde in html datei. 
	            	//Wir speichern das nochmal ab f�r n�tzlichen einsatz des webservers
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
