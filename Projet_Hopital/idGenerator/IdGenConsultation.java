package idGenerator;

import java.util.Random;

public class IdGenConsultation {
	
	public static int getIdGenConsultation() {
		return (new Random().nextInt(2500))+7;
	}

}
