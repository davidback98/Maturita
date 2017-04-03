package util;
import java.util.Random;

public class NumeroCasuale {
	private Random rnd;
	
	public NumeroCasuale(){
		rnd = new Random();
	}
	
	public int numero(){
		return rnd.nextInt(10)+1;
	}
}
