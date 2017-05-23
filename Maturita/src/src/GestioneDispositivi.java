package src;

import java.io.*;
import java.net.*;

import util.NumeroCasuale;


public class GestioneDispositivi implements Runnable {
	private Socket clientSocket;
	String addrClient;

	public GestioneDispositivi(Socket clientSocket) throws IOException{
		 this.clientSocket = clientSocket;
		 new Thread(this).start();
	}
	
	public void run(){
		OutputStream out;
		try{
			BufferedReader inDaClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			char[] stringaVett = new char[5];
			char[] statoVett = new char[8];
			inDaClient.read(stringaVett);
			String stringa = String.copyValueOf(stringaVett);
			if(stringa.contains("hello")){
				NumeroCasuale num = new NumeroCasuale();
				Integer n;
				do{
					n = num.numero();
				}while(Codici.codicePresente(n));
				out = clientSocket.getOutputStream();
				out.write(n);
				System.out.println(n);
				addrClient = clientSocket.getInetAddress().toString();
				inDaClient.read(statoVett);
				String stato = String.copyValueOf(statoVett);
				GestioneDatabase.inserisciDati(n, stato);
				GestioneDatabase.inserisciDispositivi(n, addrClient);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
