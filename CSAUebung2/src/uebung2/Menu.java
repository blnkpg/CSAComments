package uebung2;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;

public class Menu {
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	public Menu(PrintWriter out , BufferedReader in){
		this.out = out;
		this.in = in;
	}
	
	/**
	 * Schreibt das Menü Telnet Komform
	 * @return
	 */
	public String printMenu(){
		return "Aufgabe auswaehlen \n\r[1] Aufgabe 1: Inetadress \n\r[2] Aufgabe 2: Unix Daytime \n\r[3] Aufgabe 3: NTP Time \n\r[4] Aufgabe 4: HTTP Client\n\r[0] Beenden";
	}

	/**
	 * Startet die aufgaben gemäß des überegebenen Wertes und gibt das resultat als string zurück.
	 * @param auswahl
	 * @return
	 */
	public String auswahl(String auswahl){
		InetAddress address = Aufgabe1.run(this.out , this.in);
		String result = "";
		
		switch (auswahl) {
		case "1": break;
		case "2":
			result = Aufgabe2.run(address);
			break;
		case "3":
			result = Aufgabe3.run(address, this.out);
			break;
		case "4":
			result = Aufgabe4.run(address);
			break;
		case "0":
			result = "Exit";
			break;
		default:
			result = auswahl + " ist ein ungueltiger Wert.";
			break;
		}
		
		return result ;	
	}
}
