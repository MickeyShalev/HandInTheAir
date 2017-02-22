package Controller.Validators;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
//Added Comment
import java.util.Arrays;
import java.util.List;


/**
 * This class valiges age property on given date
 * @author nisan & mickey
 */
public class AgeValidator {

    /**
     * Method to validate age given date parameters 
     * @param birthday
     * @param min
     * @return 
     */
	public static boolean ValidateAge(Date birthday, int min){
		Date today = new Date();
		if (birthday.after(today)) {
			return false;
		}
		
                //System.out.println(today.getYear() - birthday.getYear());
		if ((today.getYear() - birthday.getYear() - min) > 0){
                    return true;
                }
		
		
		return false;
	}
	
}
