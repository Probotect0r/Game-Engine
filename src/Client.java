import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Client implements Runnable {
	
	DatagramSocket socket;
	InetAddress ipAddress; 
	int port;
	
	
	public Client(String ipAddress, int port){
		try {
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e) {
			System.out.println("Could not get IP address");
		}
		this.port = port;
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println("Socket could not be created");
		}
		
	}
	
	public void tick(){
		
	}
	
	public void sendPosition(){
		byte[] data = new byte[1024];
		String test = "paul";
		data = test.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.out.println("Client could not send packet.");
		}
		
	}
	
	public void update(){
		
	}

	@Override
	public void run() {
		
	}

}
