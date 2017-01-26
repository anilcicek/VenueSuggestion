package algorithms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connections.DbConnection;
import model.VenueModel;

public class UserToUserSimilarity2 {

	DbConnection dbConnection = new DbConnection();
	public List<VenueModel> findSimilarVenues(String userId) throws Exception{
		List<VenueModel> similarVenues = new ArrayList<VenueModel>();
		List<VenueModel> returnedList = new ArrayList<VenueModel>();
		List<String> exceptVenues = new ArrayList<String>();
		Statement statement = dbConnection.getConn();
		Statement statement2 = dbConnection.getConn();
		Statement statement3 = dbConnection.getConn();
		ResultSet res = null;
		ResultSet res3 = null;
		
			
			res = statement2.executeQuery("select top 11 c1.user_id, c2.user_id, count(*) as sayi from checkintrain c1, checkintrain c2 where c1.venue_id = c2.venue_id and c1.user_id = '"+userId+"' group by c1.user_id, c2.user_id order by sayi desc;");
			res.next();
			while(res.next()){
				res3 = statement3.executeQuery("select VenueId, Latitude, Longitude from PolsUpdated where VenueId='"+res.getString(2)+"'");
				res3.next();
				VenueModel model = new VenueModel();
				model.setVenueId(res3.getString(1));
				model.setLatitude(res3.getString(2));
				model.setLongitude(res3.getString(3));
				model.setCheckinCount(res.getInt(3));
				similarVenues.add(model);
				exceptVenues.add(res3.getString(1));
			}
			
			
		
		QuickSort sort = new QuickSort();
		similarVenues = sort.sort(similarVenues);
		ResultSet finalReport = null;
		
		for(int i=similarVenues.size()-1; i>similarVenues.size()-11; i--){
			
			String query1="select venue_id, count(*) from checkintest where venue_id='"+similarVenues.get(i).getVenueId()+"' and user_id='"+userId+"' group by venue_id;";
    		finalReport = statement.executeQuery(query1);
    		
    		if(finalReport.next()){
        		System.out.println(similarVenues.get(i).getVenueId()+" : +++++"); 
        		similarVenues.get(i).setVisited(true);
    		}else{
    			System.out.println(similarVenues.get(i).getVenueId()+" : -----");
    			similarVenues.get(i).setVisited(false);
    		}
    		
    		returnedList.add(similarVenues.get(i));
			
		}
	
		return returnedList;
	}

}
