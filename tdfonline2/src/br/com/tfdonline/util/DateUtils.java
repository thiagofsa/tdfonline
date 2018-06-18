package br.com.tfdonline.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	
	
	  // Returns age given the date of birth
	
	    public static int getAge(Date datanasc) throws Exception {
	
	        Calendar birth = Calendar.getInstance();
	        birth.setTime(datanasc);
	        		
	    	Calendar today = Calendar.getInstance();
	
	        int curYear = today.get(Calendar.YEAR);
	
	        int dobYear = birth.get(Calendar.YEAR);
	
	        int age = curYear - dobYear;
	
	        // if dob is month or day is behind today's month or day
	
	        // reduce age by 1
	
	        int curMonth = today.get(Calendar.MONTH);
	
	        int dobMonth = birth.get(Calendar.MONTH);
	
	        if (dobMonth > curMonth) { // this year can't be counted!
	
	            age--;
	
	        } else if (dobMonth == curMonth) { // same month? check for day
	
	            int curDay = today.get(Calendar.DAY_OF_MONTH);
	
	            int dobDay = birth.get(Calendar.DAY_OF_MONTH);
	
	            if (dobDay > curDay) { // this year can't be counted!
	
	                age--;
	
	            }
	
	        }
	
	        return age;
	
	    }

			
			
			
	     public static void main (String args[]) throws Exception {
	    	 
	    	 
	    	 Calendar dob = Calendar.getInstance();

	    	 dob.set(1984, 5, 16);
	    	 
	    	 Date datanasc = dob.getTime();
	    	 
	    	 System.out.println(getAge(datanasc));
	    	 
	    	 
	    	 
	     }
	    

}
