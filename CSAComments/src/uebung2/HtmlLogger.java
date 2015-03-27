package uebung2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.net.ntp.TimeStamp;

import com.sun.java_cup.internal.runtime.Scanner;

//Thread = im Hintergrund
public class HtmlLogger extends Thread {
	
		//Zeiger auf Datei (c:\\temp...) damit mit dieser Datei weitergearbeitet werden kann
		private static File index = new File("c:\\tmp\\test\\index.html");
		
		//führt run() funktion aus, siehe weiter unten
		public HtmlLogger(){
			run();
		}
		
		public static void write(String entry){
			//wenn index nicht lesen oder schreiben kann dann brich ab
			if (!index.canRead() || !index.canWrite()){
				return;
			}
			try {
				BufferedReader reader = new BufferedReader(new FileReader(index));
				//verkettete Liste von Strings. Kein array weil effizienter, und keine feste Länge!
				LinkedList <String> lines = new LinkedList<String>();
				String line = null;
				//lese datei ein in schleife
				while( (line = reader.readLine()) != null ){
					// "/body" in Zeile? /body beendet html body
					if(line.contains("</body>")){
						//füge zu verketteter Liste den entry eintrag zu. Damit linked list mit html tags ist
						lines.add(getTime() + entry);
						lines.add(line);
					}else{
						lines.add(line);
					}
				}
				
				//verkettete liste in "index" einspeichern
				BufferedWriter writer = new BufferedWriter(new FileWriter(index));
				// lines verkettete liste durchgehen, und als datei abspeichern. Schreibt direkt in datei. 
				for (String cachedLine : lines) {
					writer.write(cachedLine);
				}
				
				
				
				writer.close();
				
				
				reader.close();
				
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		
		
		@Override
		public void run() {
			//if Datei auf die ich zeige noch existiert?
			if(!index.exists()){
				BufferedWriter writer;
				try {
					writer = new BufferedWriter(new FileWriter(index));
					//String Array mit Standard Befehlen die jede html Datei braucht
					String []htmlTags = {"<html>","<head>","</head>","<body>","</body>","</html>"}; 
					//geht array durch und jedes element mit tag ansprechen. 
					for (String tag : htmlTags) {
						//In Datei schreiben. (und eigentlich eine neue zeile aber i.wie auch nicht)
						writer.write(tag + "\n\r");
						//und jetzt wirklich eine neue zeile
						writer.newLine();
					}
					writer.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		private static String getTime(){
			return new TimeStamp(new Date()).toDateString() + " : ";
		}
		
		
}
