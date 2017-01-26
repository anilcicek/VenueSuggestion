package senior.project;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.introspect.BasicClassIntrospector.GetterMethodFilter;

import algorithms.FindHomeLocation;
import algorithms.QuickSort;
import algorithms.findpopulervenue;
import connections.DbConnection;
import model.Geocode;
import model.VenueModel;
import model.DateFormat;
/**
 * Hello world!
 *
 */
public class App 
{
	
	
    public static void main( String[] args )
    {   
    	DbConnection dbConnection = new DbConnection();
        FindHomeLocation obje = new FindHomeLocation();
        findpopulervenue obj = new findpopulervenue();
        int i;
        try {
        	//Geocode konum= new Geocode();
        	//Statement statement = dbConnection.getConn();
        	//Statement statement2 = dbConnection.getConn();
        	//ResultSet users =null;
        	//String getuserid="select user_id from users ";
        	//users = statement.executeQuery(getuserid);
        	//while(users.next())
        	//{

        	
        	
        	
    			//konum=obje.find(users.getString(1));
    			//String edituserlocation="INSERT INTO usersupdated(user_id,lat,long) VALUES ('"+users.getString(1)+"','"+konum.getLatitude()+"','"+konum.getLongitude()+" ')";
    			//statement2.execute(edituserlocation);
    			
        //	}
			
        	String userId="1642";
        	//obj.findnearpopulervenue(userId);
        	/*List<VenueModel> venuelist =obj.findnearpopulervenue(userId);
        	Statement statement = dbConnection.getConn();
        	ResultSet finalReport = null;
        	String query = "";
        	for(i=0; i<venuelist.size(); i++){
        		query="select venue_id, count(*) from checkintest where venue_id='"+venuelist.get(i).getVenueId()+"' and user_id='"+userId+"' group by venue_id;";
        		finalReport = statement.executeQuery(query);
        		if(finalReport.next()){
            			if(venuelist.get(i).getVenueId().equals(finalReport.getString(1)))
            				System.out.println(finalReport.getString(1));
            			else
            				System.out.println("sakso");
            		
        		}else{
        			System.out.println("sex");
        		}
        		
        	}*/
        	i=0;
			
        	/*
        	Statement statement = dbConnection.getConn();
        	Statement statement2 = dbConnection.getConn();
        	ResultSet users =null;
        	String getuserid="select * from checkinist ";
        	String insertnewcheckin="";
        	String yazi="";
        	String tarih="";
        	users=statement.executeQuery(getuserid);
        	DateFormat date = new DateFormat();
        	i=2936501;
        	users.absolute(2936501);
        	while(users.next())
        	{
        		yazi=users.getString(3).substring(4, 7);
        		yazi=date.getmonth(yazi);
        	    tarih=users.getString(3).substring(26, 30)+"-"+yazi+"-"+ users.getString(3).substring(8, 10)+" "+users.getString(3).substring(11, 19);
        		insertnewcheckin="Insert into checkinlast (user_id,venue_id,time,timezone ) values ('"+users.getString(1) +" ','"+ users.getString(2)+"', '"+tarih +"','"+users.getString(4)+"' )";
        		System.out.println(i);
        		statement2.execute(insertnewcheckin);
        	    
        	    i++;
        	}
        	
        	
        	
        	
        	
        	
        	
        	*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
