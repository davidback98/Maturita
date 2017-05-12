package src;

import java.io.*;
import java.net.*;

import util.NumeroCasuale;


public class GestioneDispositivi implements Runnable {
	private Socket clientSocket;

	public GestioneDispositivi(Socket clientSocket) throws IOException{
		 this.clientSocket = clientSocket;
		 new Thread(this).start();
	}
	
	public void run(){
		OutputStream out;
		try{
			BufferedReader inDaClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			char[] buffer = new char[5];
			inDaClient.read(buffer);
			String res = String.copyValueOf(buffer);
			if(res.contains("hello")){
				NumeroCasuale num = new NumeroCasuale();
				Integer n;
				do{
					n = num.numero();
				}while(Codici.codicePresente(n));
				Codici.assegnazioneCodice(n);
				out = clientSocket.getOutputStream();
				out.write(n);
				System.out.println(n);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
