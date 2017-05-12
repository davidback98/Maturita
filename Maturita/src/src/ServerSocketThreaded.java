package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketThreaded implements Runnable {

	private int porta;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	
	public ServerSocketThreaded(int porta) throws IOException{
		this.porta = porta;
		new Thread(this).start();
	}
	@Override
	public void run() {
			try {
				serverSocket = new ServerSocket(porta);
				do{
					clientSocket = serverSocket.accept();
					GestioneDispositivi gestioneDispositivi = new GestioneDispositivi(clientSocket);
				}while(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}