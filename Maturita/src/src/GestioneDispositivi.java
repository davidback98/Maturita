package src;

import java.io.*;
import java.net.*;

import javax.swing.SingleSelectionModel;

import util.NumeroCasuale;


public class GestioneDispositivi implements Runnable {
	private Socket clientSocket;
	String addrClient;

	public GestioneDispositivi(Socket clientSocket) throws IOException{
		 this.clientSocket = clientSocket;
		 new Thread(this, "Gestione dispositivi").start();
	}
	
	public void run(){
		OutputStream out;
		try{
			Integer n = 0;
			BufferedReader inDaClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String packetText = inDaClient.readLine();
			if(packetText.equals("Hello")){
				NumeroCasuale num = new NumeroCasuale();
				do{
					n = num.numero();
				}while(Codici.codicePresente(n));
				out = clientSocket.getOutputStream();
				out.write(n);
				addrClient = clientSocket.getInetAddress().toString();
				GestioneDatabase.inserisciDispositivi(n, addrClient);
				System.out.println(n);
				//Thread end
			}else if(packetText.contains("Aperto") || packetText.contains("Chiuso")){
				//n = inDaClient.read() -48;//ASCII '0' = 48
				String[] stato = packetText.split("-");
				n= Integer.parseInt(stato[0]);
				System.out.println(packetText);
				//System.out.println(n);
				GestioneDatabase.inserisciDati(n, stato[1]);
				
			}
			else{
				System.out.println("Pacchetto non riconosciuto");
			}
			clientSocket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
