package algorithms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connections.DbConnection;
import model.VenueModel;

public class VenueToVenueSimilarity {
	
	DbConnection dbConnection = new DbConnection();
	public List<VenueModel> findSimilarVenues(String userId) throws Exception{
		List<VenueModel> similarVenues = new ArrayList<VenueModel>();
		List<VenueModel> returnedList = new ArrayList<VenueModel>();
		List<String> exceptVenues = new ArrayList<String>();
		
		String exceptQuery="";
		Statement statement = dbConnection.getConn();
		Statement statement2 = dbConnection.getConn();
		Statement statement3 = dbConnection.getConn();
		ResultSet res = null;
		ResultSet res3 = null;
		String query = "select top 5 venue_id, count(*) as c from checkintrain where user_id = '"+userId+"' group by venue_id order by c desc;";
		ResultSet topVenues = statement.executeQuery(query);
		while(topVenues.next()){
			for(int i=0; i<exceptVenues.size(); i++){
				exceptQuery+=" and c2.venue_id != '"+exceptVenues.get(i)+"' ";
			}
			res = statement2.executeQuery("select top 5 c1.venue_id, c2.venue_id, count(*) as sayi from checkintrain c1, checkintrain c2 where c1.user_id = c2.user_id and c1.venue_id = '"+topVenues.getString(1)+"' "+exceptQuery+" group by c1.venue_id, c2.venue_id order by sayi desc;");
			res.next();
			while(res.next()){
				res3 = statement3.executeQuery("select VenueId, Latitude, Longitude,NeighborhoodCode from PolsUpdated where VenueId='"+res.getString(2)+"'");
				res3.next();
				VenueModel model = new VenueModel();
				model.setVenueId(res3.getString(1));
				model.setLatitude(res3.getString(2));
				model.setLongitude(res3.getString(3));
				model.setCheckinCount(res.getInt(3));
				model.setNeighborhoodCode(res3.getString(4));
				similarVenues.add(model);
				exceptVenues.add(res3.getString(1));
			}
			
			
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
