package uebung2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;

public class Aufgabe3 {

	/**
	 * Spricht das übergebene InetAdress objekt über den NTP port 
	 * mithilfe eines NTP Clients an und gibt zwischen informationen direkt in 
	 * der Telnet-Console aus und gibt das endresultat als String zurück
	 * 
	 * @param address
	 * @param out
	 * @return
	 */
	public static String run(InetAddress address , PrintWriter out) {
		if(address == null){
			return "No URL entered";
		}
		NTPUDPClient ntpClient = new NTPUDPClient();
		try {
			TimeInfo timeStamp = ntpClient.getTime(address);
			NtpV3Packet nachricht = timeStamp.getMessage();
			
			out.write("Stratum: " + nachricht.getModeName());
			/*
			 * http://www.urz.uni-heidelberg.de/netz/ntp.html
			 * Version: Die NTP Version
			 * Mode:
			 * poll:
			 * precision: -6 abweichung von 6 ms
			 * delay: 2048 die verbindungsdauer
			 * dispersion:
			 */
			
			out.write(nachricht.toString());
			
			Date uebergebeneZeit = new Date(nachricht.getTransmitTimeStamp().getTime());
			//TODO gibt nnichts aus
			return "Die Zeit vom Server " + address.getHostName() + " : "  + uebergebeneZeit;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Error";
	}

}
