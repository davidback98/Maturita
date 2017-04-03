package src;

import util.NumeroCasuale;

public class Maturita {

	public static void main(String[] args){
		//ServerSocketThreaded serverSocket = new ServerSocketThreaded(10000);
		//Broadcast broadcastTh = new Broadcast(1000,10000);
		NumeroCasuale num = new NumeroCasuale();
		int n = num.numero();
		System.out.println(n);
		if (Codici.codicePresente(n))
			System.out.println("Presente");
		else{
			System.out.println("Assente");
			Codici.assegnazioneCodice(n);
		}
	}

}
