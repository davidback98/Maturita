package src;

import java.io.IOException;

import util.NumeroCasuale;

public class Maturita {
	static final int serverPort 	= 10000;
	static final int broadcastPort 	= 1000;
	
	public static void main(String[] args) throws IOException{
		ServerSocketThreaded serverSocket = new ServerSocketThreaded(serverPort);
		Broadcast broadcastTh = new Broadcast(broadcastPort, serverPort);
	}

}
