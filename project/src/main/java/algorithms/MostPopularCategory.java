package algorithms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connections.DbConnection;
import model.Geocode;
import model.VenueModel;

public class MostPopularCategory {

	DbConnection dbConnection = new DbConnection();
	public List<VenueModel> SuggestPopulerCategoryVenues(String userId,Geocode konum) throws Exception{

		List<VenueModel> venuelist = new ArrayList<VenueModel>();
		Statement statement = dbConnection.getConn();
		Statement statement2 = dbConnection.getConn();
		Statement statement3 = dbConnection.getConn();
		Statement statement4 = dbConnection.getConn();
		//String userlat;
		//String userlong;
		
		ResultSet categories = null;
		ResultSet allVenues = null;
		ResultSet countcheckin=null;
		//categories=statement.executeQuery("select lat,long from usersupdated where user_id= '"+userId+"'");
		//categories.next();
		//userlat=categories.getString(1);
		//userlong=categories.getString(2);
		String kategorigetir="Select VenueCategory , count(*) as a from PolsUpdated where  VenueId in ( select venue_id from checkintrain where user_id='"+userId+"') group by VenueCategory order by a desc";
		categories=statement3.executeQuery(kategorigetir);
		String mekanGetir="";
		int i=0;
		while(categories.next() && i<1)
		{
			mekanGetir="select top 100   p.VenueId,SQRT(SQUARE(p.Latitude-"+konum.getLatitude()+")+SQUARE(p.Longitude-"+konum.getLongitude()+" ))  AS A , p.Latitude,p.Longitude  from PolsUpdated p  where VenueCategory='"+categories.getString(1) +"' ORDER BY A ASC";
		      allVenues=statement4.executeQuery(mekanGetir);
		      while(allVenues.next())
		    	  {
		    	  VenueModel venue = new VenueModel();
					String sql_3="Select count(*) from checkintrain where venue_id='"+allVenues.getString(1) +"'"; 
					countcheckin =statement2.executeQuery(sql_3);// herbir mekanın checkin sayısı bulundu
					//checkin sayısı en yüksek olan 5 mekan kullanıcıya önerilecek id leri bir dizide saklanacak
					countcheckin.next();
					venue.setCheckinCount(countcheckin.getInt(1));
					venue.setVenueId(allVenues.getString(1));
					venue.setLatitude(allVenues.getString(3));
					venue.setLongitude(allVenues.getString(4));
					venuelist.add(venue);
		    	  }
		      i++;
		}
		QuickSort quick = new QuickSort();
    	List<VenueModel> sortedList = quick.sort(venuelist);
    	List<VenueModel> returnedList = new ArrayList<VenueModel>();
    	ResultSet finalReport = null;
    	String query = "";
    	for( i=sortedList.size()-1;i>sortedList.size()-11;i--)
    	{
    		//System.out.println("VenueID:"+sortedList.get(i).getVenueId()+ " Checkin Sayısı:"+sortedList.get(i).getCheckinCount());
    		
        	
        		query="select venue_id, count(*) from checkintest where venue_id='"+sortedList.get(i).getVenueId()+"' and user_id='"+userId+"' group by venue_id;";
        		finalReport = statement.executeQuery(query);
        		
        		if(finalReport.next()){
            		System.out.println(finalReport.getString(1)+" : +++++"); 
            		sortedList.get(i).setVisited(true);
        		}else{
        			System.out.println(sortedList.get(i).getVenueId()+" : -----");
        			sortedList.get(i).setVisited(false);
        		}
        		
        		returnedList.add(sortedList.get(i));
    	}
		
		
	  return returnedList;		
	}
}
