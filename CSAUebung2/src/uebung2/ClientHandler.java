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
	public ClientHandler(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	/**
	 * Schreibt ddie Menüs und und leitet Benutzereingaben für die weitere verarbeitung weiter
	 */
	@SuppressWarnings("null")
	public void run(){
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.menu = new Menu(out , in);
			String input = "";
			String output = "";
			
			while (true) {
				out.println(menu.printMenu());
				input = in.readLine();
				if (!input.equals("0")){
					output = menu.auswahl(input);
	            	out.println(output);
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
