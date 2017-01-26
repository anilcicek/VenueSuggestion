package algorithms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connections.DbConnection;
import model.Geocode;
import model.VenueModel;

public class findpopulervenue {
	DbConnection dbConnection = new DbConnection();
	public List<VenueModel> findnearpopulervenue(String userId,Geocode konum) throws Exception
	{
		//String userlat;
		//String userlong;
		List<VenueModel> venuelist = new ArrayList<VenueModel>();
		Statement statement2 = dbConnection.getConn();
		//ResultSet userinfo = null;
		//Statement statement = dbConnection.getConn();
		ResultSet venues = null;
		Statement statement3 = dbConnection.getConn();
		ResultSet countcheckin = null;
		//venues= statement.executeQuery("select lat,long from usersupdated where user_id= '"+userId+"'");// kullanıcının koordinatları
		//venues.next();
		//userlat= venues.getString(1) ;
		//userlong= venues.getString(2);
		String sql_1 = "select top 100  p.VenueId,SQRT(SQUARE(p.Latitude-"+konum.getLatitude() +")+SQUARE(p.Longitude-"+konum.getLongitude() +" ))  AS A , p.Latitude,p.Longitude from PolsUpdated p ORDER BY A ASC";
		venues=statement2.executeQuery(sql_1);// kullanıcıya en yakın olan 50 mekan listelendi
		while(venues.next())
		{

			VenueModel venue = new VenueModel();
			String sql_3="Select count(*) from checkintrain where venue_id='"+venues.getString(1) +"'"; 
			countcheckin =statement3.executeQuery(sql_3);// herbir mekanın checkin sayısı bulundu
			//checkin sayısı en yüksek olan 5 mekan kullanıcıya önerilecek id leri bir dizide saklanacak
			countcheckin.next();
			venue.setCheckinCount(countcheckin.getInt(1));
			venue.setVenueId(venues.getString(1));
			venue.setLatitude(venues.getString(3));
			venue.setLongitude(venues.getString(4));
			venuelist.add(venue);
		
		}
		
		QuickSort quick = new QuickSort();
    	List<VenueModel> venueliste = quick.sort(venuelist);
    	Statement statement4 = dbConnection.getConn();
    	List<VenueModel> returnedList = new ArrayList<VenueModel>();
    	ResultSet finalReport = null;
    	String query = "";
    	for(int i=venueliste.size()-1;i>venueliste.size()-11;i--)
    	{
    		//System.out.println("VenueID:"+venueliste.get(i).getVenueId()+ " Checkin Sayısı:"+venueliste.get(i).getCheckinCount());
    		
        	
        		query="select venue_id, count(*) from checkintest where venue_id='"+venueliste.get(i).getVenueId()+"' and user_id='"+userId+"' group by venue_id;";
        		finalReport = statement4.executeQuery(query);
        		if(finalReport.next()){
            		System.out.println(finalReport.getString(1));
            		venueliste.get(i).setVisited(true);
        		}else{
        			System.out.println("---");
        			venueliste.get(i).setVisited(false);
        		}
        		
        		returnedList.add(venueliste.get(i));
    	}
		
		return returnedList;
	}

}
