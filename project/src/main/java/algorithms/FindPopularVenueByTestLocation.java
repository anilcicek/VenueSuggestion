package algorithms;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connections.DbConnection;
import model.VenueModel;

public class FindPopularVenueByTestLocation {


	DbConnection dbConnection = new DbConnection();
	public List<VenueModel> SuggestVenuebyTestLocation(String userId) throws Exception{
		

    	List<VenueModel> returnedList = new ArrayList<VenueModel>();
		List<VenueModel> venuelist = new ArrayList<VenueModel>();
		Statement statement = dbConnection.getConn();
		ResultSet suggestlocations = null;
		Statement statement2 = dbConnection.getConn();

		Statement statement3 = dbConnection.getConn();
		ResultSet suggestvenues = null;
		ResultSet countcheckin = null;
		String getsuggestlocations="select Latitude, Longitude from PolsUpdated where VenueId in ( select  TOP 10 venue_id  from checkintest where user_id='"+userId+"' group by venue_id order by count(*) desc)";
		suggestlocations=statement.executeQuery(getsuggestlocations);
		
		while(suggestlocations.next())
		{	
			String enyakin50 = "select top 50  p.VenueId,SQRT(SQUARE(p.Latitude-"+suggestlocations.getString(1) +")+SQUARE(p.Longitude-"+ suggestlocations.getString(2) +" ))  AS A , p.Latitude,p.Longitude, p.NeighborhoodCode from PolsUpdated p ORDER BY A ASC";
            suggestvenues=statement2.executeQuery(enyakin50);
            
            while(suggestvenues.next())
            {
            	
            	VenueModel venue = new VenueModel();
    			String sql_3="Select count(*) from checkintrain where venue_id='"+suggestvenues.getString(1) +"'"; 
    			countcheckin =statement3.executeQuery(sql_3);// herbir mekanın checkin sayısı bulundu
    			//checkin sayısı en yüksek olan 5 mekan kullanıcıya önerilecek id leri bir dizide saklanacak
    			countcheckin.next();
    			venue.setCheckinCount(countcheckin.getInt(1));
    			venue.setVenueId(suggestvenues.getString(1));
    			venue.setLatitude(suggestvenues.getString(3));
    			venue.setLongitude(suggestvenues.getString(4));
    			venue.setNeighborhoodCode(suggestvenues.getString(5));
    			venuelist.add(venue);
            	
            	
            }
            QuickSort quick = new QuickSort();
        	List<VenueModel> venueliste = quick.sort(venuelist);
        	Statement statement4 = dbConnection.getConn();
        	ResultSet finalReport = null;
        	String query = "";
        	int k=venueliste.size()-1;
        	int l=venueliste.size()-1;
        	
        	while(k>venueliste.size()-2)
        	{

        		//System.out.println("VenueID:"+venueliste.get(i).getVenueId()+ " Checkin Sayısı:"+venueliste.get(i).getCheckinCount());
        		
            	
            		
            		int cikis=0;
            		for(int i=0;i<returnedList.size();i++)
            		{
            			if(returnedList.get(i).getVenueId().equals(venueliste.get(l).getVenueId()))
            			{
            				cikis=1;
            			}
            			
            		}
            		
            		if(cikis==1)//içinde varmı diye nasıl kontrol eder sor???
            				{
            			      k++;
            			      l--;
            				}
            		else
            		{
            			query="select venue_id, count(*) from checkintest where venue_id='"+venueliste.get(l).getVenueId()+"' and user_id='"+userId+"' group by venue_id;";
            		   finalReport = statement4.executeQuery(query);
            			if(finalReport.next()){
	                		System.out.println(venueliste.get(l).getVenueId());
	                		venueliste.get(l).setVisited(true);
	            		}else{
	            			System.out.println("---");
	            			venueliste.get(l).setVisited(false);
	            		}

	            		returnedList.add(venueliste.get(l));
            		}
        		
        		
        		k--;
        	}

            venuelist.clear();
            venueliste.clear();
            
            
			
		}
		
		
		
		
		return returnedList;
	}
	
}
