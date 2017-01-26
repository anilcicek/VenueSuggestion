package senior.project;

import java.sql.ResultSet;
import java.sql.Statement;

import algorithms.FindHomeLocation;
import connections.DbConnection;
import model.Geocode;

public class mekancountekleme {

	public static void main(String[] args) throws Exception {

		DbConnection dbConnection = new DbConnection();
        Statement statement1 = dbConnection.getConn();
		Statement statement2 = dbConnection.getConn();
		ResultSet venues = statement2.executeQuery("Select * from PolsUpdated");
		int i=1;
		ResultSet countcheckin= null;
		while(venues.next())
		{
  
			 countcheckin= statement1.executeQuery("Select COUNT(*) from checkintrain where venue_id='"+venues.getString(1)+"'");
			 countcheckin.next();
		    statement1.execute("Insert into PolsUpdatedLast (VenueId,Latitude,Longitude,VenueCategory,CountryCode,CityCode,NeighborhoodCode,DistrictCode,CountCheckin) values('"+venues.getString(1)+"','"+venues.getString(2)+"','"+venues.getString(3)+"','"+venues.getString(4)+"','"+venues.getString(5)+"','"+venues.getString(6)+"','"+venues.getString(7)+"','"+venues.getString(8)+"','"+countcheckin.getString(1)+"') ");
			i++;
			System.out.println(i);
		}
		
		
	
	}

}
