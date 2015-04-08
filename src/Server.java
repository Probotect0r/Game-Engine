import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class Server implements Runnable{
	
	DatagramSocket socket;
	int port;
	
	public Server(int port) {
		 this.port = port;
		 try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println("Server could not listen on port");
		}
		 
		
	}
	
	public void startListening(){	
		new Thread(this).start();		
	}

	
	public void run() {
		while(true){
			byte[] data = new byte[1024];			
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				System.out.println("Socket can't recieve packets");
			}
			
			String message = new String(packet.getData());
			message = message.trim();
			
			System.out.println("Server = Message recieved from client: " + message);	
			
		}
		
	}

}
