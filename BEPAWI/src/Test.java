import java.time.*;
import java.util.Date;

import javax.swing.JOptionPane;

public class Test {
	
	public static Date dateNow;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d = new Date();
		LocalDate ld = LocalDate.now();
		
		System.out.println(d);
		System.out.println(ld);
		

	}

}
