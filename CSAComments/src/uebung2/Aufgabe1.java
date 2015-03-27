package uebung2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

public class Aufgabe1 {

	/**
	 * Erzeugt ein InetAdress Objekt aus der eingegebenen URL
	 * 
	 * @param out
	 * @param in
	 * @return Objekt aus der eingegebenen URL
	 */
	//PrintWriter out, um von unserem Server auf Client Konsole zu schreiben
	static public InetAddress run(PrintWriter out , BufferedReader in){
		
		out.println("Geben sie eine URL an (ohne http://): ");
		String url;
		//readLine: liest benutzereingabe ein
		try {
			url = in.readLine();
			
			url = url.replaceAll("\b", "");
			boolean isIP = false;
			//byte[] InetAddress-Object soll erzeugt werden. IP Adresse erfordert 4 Bytes.
			byte[] ipV4Adress = new byte[4];
			try {
				//übergebener String wird angeschaut, Punkte entfernen. Wenn Zahl, dann string = IP
				isIP = (Long.valueOf(url.replace(String.valueOf('.'), "")) != 0)? true: false;
				String temp = url;
				int stelle = 0;
				int i = 0;
				do{
					//von 0ter Stelle bis Stelle mit dem Punkt und Konvertiere String zu Int zu byte.
					ipV4Adress[i] = Integer.valueOf(temp.substring(0, ((stelle = temp.indexOf("."))<0)?temp.length():stelle )).byteValue();
					//Schneide Zahlen bis Punkt weg, gucke nächste Zahlen an
					temp = temp.substring(stelle+1);
					System.out.println(temp);
					i++;
				}while(i<=3);
				
			} catch (NumberFormatException e) {
				
			}
			
			// = wenn IP ist dann hole InternetAdresse mit ipv4 Adress. (Funktioniert nur mit ipv4!)
			InetAddress adresse = (isIP)?InetAddress.getByAddress(ipV4Adress):InetAddress.getByName(url);
			
			if(isIP){
				//Wenn ich IP habe, dann gib Name aus (google.de)
				url += " : "  + adresse.getHostName();
			}else{
				//Wenn ich Adresse habe dann gib IP
				url += " : "  + adresse.getHostAddress();
			}
			
			out.println("\n\r" + url);
			
			return adresse;
		
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		
	}
	
}
