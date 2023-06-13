package idGenerator;

import java.util.Random;

public class IdGenPatients {
	
	public static int genId() {
		return new Random().nextInt(2500)+7;
	}

}
