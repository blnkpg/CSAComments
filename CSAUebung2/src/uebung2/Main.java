package uebung2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	/**
	 * Rudimentäre auswahl
	 * @param args
	 * @author Christopher Lucas
	 */
	public static void main(String[] args) {
		int auswahl = 2;
		
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
				threadPool.submit(new TelnetServer());
				threadPool.submit(new WebServ());
			System.out.println("Server gestartet");	
				
//		TelnetServer telnetServer = new TelnetServer();
//		System.out.println("TelnetServer started");
//		WebServ webserver = new WebServ();
//		System.out.println("WebServer started");
//		
//		System.out.println("[1] Local Mode" +
//							"\n[2] Enable Telnet" + 
//							"\n[0] / [Enter] Exit");
//		 auswahl = Eingabe.leseIntEin();
		switch (auswahl) {
			case 1:
//				while(runMenu());
			break;
			case 2:
				
			break;
					
		default:
			break;
		}
	}

//	private static boolean runMenu(){
//		System.out.println("Aufgabe auswaehlen");
//		System.out.println("[1] Aufgabe 1: Inetadress");
//		System.out.println("[1] Aufgabe 2: Unix Daytime");
//		System.out.println("[1] Aufgabe 3: NTP Time");
//		System.out.println("[1] Aufgabe 4: HTTP Client");
//		
//		int auswahl = Eingabe.leseIntEin();
//		InetAddress address = Aufgabe1.run(System.out , new BufferedReader(new InputStreamReader(System.in)));
//		
//		switch (auswahl) {
//		case 2:
//			Aufgabe2.run(address);
//			break;
//		case 3:
//			Aufgabe3.run(address);
//			break;
//		case 4:
//			Aufgabe4.run(address);
//			break;
//		case 0:
//			return false;
//		default:
//			break;
//		}
//		
//		return true;
//	}
	
}
