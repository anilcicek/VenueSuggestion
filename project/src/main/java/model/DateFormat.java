package model;

public class DateFormat {

	
	String date="";
	public String getmonth(String ay)
	{
		
		if (ay.equalsIgnoreCase("Jan")) {
			
			date="01";
		}
		else if (ay.equalsIgnoreCase("Feb")) {
			date="02";
		}
		else if (ay.equalsIgnoreCase("Mar")) {
			date="03";
		}
		else if (ay.equalsIgnoreCase("Apr")) {
			date="04";
		}
		else if (ay.equalsIgnoreCase("May")) {
			date="05";
		}
		else if (ay.equalsIgnoreCase("Jun")) {
			date="06";
		}
		else if (ay.equalsIgnoreCase("Jul")) {
			date="07";
		}
		else if (ay.equalsIgnoreCase("Aug")) {
			date="08";
		}
		else if (ay.equalsIgnoreCase("Sep")) {
			date="09";
		}
		else if (ay.equalsIgnoreCase("Oct")) {
			date="10";
		}
		else if (ay.equalsIgnoreCase("Nov")) {
			date="11";
		}
		else if (ay.equalsIgnoreCase("Dec")) {
			date="12";
		}
		
		return date;
	}
}
