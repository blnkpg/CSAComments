package uebung2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class Aufgabe2 {

	/**
	 * Spricht das InetAddress Objekt �ber den Unix-DayTimeServer port an und gibt die R�ckgabe als string zur�ck
	 * 
	 * time.fu-berlin.de 
	 * zeit.fu-berlin.de
	 * @param address
	 * @return
	 */
	public static String run(InetAddress address) {
		int port = 13;
		String result = "";
		try {
			//Socket stellt Verbindung vom Client zum Server dar. 
			Socket sock = new Socket(address, port);
			
			// Wenn Verbindung positiv , und Daten reinkommen, dann �ber dieses Objekt (input) ansprechen
			InputStream input = sock.getInputStream();
			// Einkommende Daten in Byte Array speichern, HIER vorbereitung des Arrays
			byte[] streamedBytes = new byte[100];
			int length = 0;
			//R�ckgabe von -1 bei keinen Daten. Einlesen der Daten vom Server. Daten in StreamedBytesArray gespeichert.
			while ((length = input.read(streamedBytes)) != -1) {
			
				// Byte Array sp�ter in andere Formate zwischenspeichern
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// ps = drucker f�r zeichenketten f�r baos.
				PrintStream ps = new PrintStream(baos);
				//Schreiben von streamedBytes Daten in ps
				ps.write(streamedBytes, 0, length);
				// Schreiben der zur�ckgegebenen Daten in String als Text
				result = baos.toString();
				
				System.out.write(streamedBytes, 0, length);
			}
			input.close();
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO Fehler zur�ckgeben wenn angesprochener server kein zeitserver ist
		return result;
		
	}
}
