package src;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.List;

public  class Broadcast implements Runnable {
	private int porta;
	private int serverPorta;
	public Broadcast(int porta, int serverPorta){
		this.porta = porta;
		this.serverPorta = serverPorta;
		new Thread(this).start();
	}
	public void run(){
		try{
			@SuppressWarnings("resource")
			DatagramSocket s = new DatagramSocket();
			DatagramPacket packet = null;
			InetAddress broadcastAddress = null;
			List<InterfaceAddress> interfaces = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getInterfaceAddresses();
			for(InterfaceAddress addresses : interfaces){
				if(addresses.getBroadcast() != null)
					broadcastAddress = addresses.getBroadcast();
			}
			String text = Integer.toString(serverPorta);
			byte[] textByte = text.getBytes();
			packet = new DatagramPacket(textByte, textByte.length, broadcastAddress, porta);
			s.setBroadcast(true);
			while(true){
				s.send(packet);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
