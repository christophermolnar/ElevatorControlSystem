package newElevatorSystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Temp {
	public static void main(String args[]) {
		try {
			//Initialize variables
			byte[] bufFromClient;
			DatagramSocket datagramSocketClient = new DatagramSocket();
			DatagramPacket packetReceive;
			
			bufFromClient = new byte[] {4, 0, 7, 0};
			packetReceive = new DatagramPacket(bufFromClient, bufFromClient.length, InetAddress.getByName("127.0.0.1"), 69);
			datagramSocketClient.send(packetReceive);
		} catch (Exception e) {
			
		}
	}
}
