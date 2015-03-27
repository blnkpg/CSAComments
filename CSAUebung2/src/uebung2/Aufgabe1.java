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
	static public InetAddress run(PrintWriter out , BufferedReader in){
		out.println("Geben sie eine URL an (ohne http://): ");
		String url;
		try {
			url = in.readLine();
			
			url = url.replaceAll("\b", "");
			boolean isIP = false;
			byte[] ipV4Adress = new byte[4];
			try {
				
				isIP = (Long.valueOf(url.replace(String.valueOf('.'), "")) != 0)? true: false;
				String temp = url;
				int stelle = 0;
				int i = 0;
				do{
				
					ipV4Adress[i] = Integer.valueOf(temp.substring(0, ((stelle = temp.indexOf("."))<0)?temp.length():stelle )).byteValue();
					temp = temp.substring(stelle+1);
					System.out.println(temp);
					i++;
				}while(i<=3);
				
			} catch (NumberFormatException e) {
				
			}
			
			
			InetAddress adresse = (isIP)?InetAddress.getByAddress(ipV4Adress):InetAddress.getByName(url);
			
			if(isIP){
				url += " : "  + adresse.getHostName();
			}else{
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
