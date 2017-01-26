package algorithms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connections.DbConnection;
import model.Geocode;
import model.UserToUserModel;
import model.VenueModel;

public class UserToUserSimilarity {
	DbConnection dbConnection = new DbConnection();
	
	public List<VenueModel> findNearestUser(String userId,Geocode konum) throws Exception
	{
		QuickSortUserToUser quick = new QuickSortUserToUser();
    	List<UserToUserModel> countlist = new ArrayList<UserToUserModel>();
    	List<UserToUserModel> sortedCountlist = new ArrayList<UserToUserModel>();

		List<VenueModel> returnedList = new ArrayList<VenueModel>();
    	
		//String userlat,userlong;
		Statement statement = dbConnection.getConn();
		//ResultSet user = null;
		//user= statement.executeQuery("select lat,long from usersupdated where user_id= '"+userId+"'");// kullanıcının koordinatları
		//user.next();
		//userlat= user.getString(1) ;
		//userlong= user.getString(2);
		Statement statement2 = dbConnection.getConn();
		ResultSet mostClosedUsers = null;
		ResultSet finalReport = null;
		String closedUsers="";
		closedUsers="select top 41  p.user_id,SQRT(SQUARE(p.lat-"+konum.getLatitude() +")+SQUARE(p.long-"+konum.getLongitude()+"  ))  AS A  from usersupdated p ORDER BY A ASC";
		mostClosedUsers=statement2.executeQuery(closedUsers);
		mostClosedUsers.next();
		while(mostClosedUsers.next())
		{
			UserToUserModel usermodel = new UserToUserModel();
			usermodel.setUserId(mostClosedUsers.getString(1));
			usermodel.setMatchedcount(compareTwoUser(userId,mostClosedUsers.getString(1) ));
            countlist.add(usermodel);
		}
		sortedCountlist = quick.sort(countlist);
		List<VenueModel> suggestList=SuggestVenueByUserSimilarity(userId, sortedCountlist.get(sortedCountlist.size()-1).getUserId(), sortedCountlist.get(sortedCountlist.size()-2).getUserId(), sortedCountlist.get(sortedCountlist.size()-3).getUserId());
		//for(int i=0;i<3;i++)
		//{
			//System.out.println(i+".user id: "+sortedCountlist.get(i).getUserId()+"----- eşleşen sayi: "+sortedCountlist.get(i).getMatchedcount());
 		//}
for(int i=suggestList.size()-1; i>suggestList.size()-11; i--){
			
			String query1="select venue_id, count(*) from checkintest where venue_id='"+suggestList.get(i).getVenueId()+"' and user_id='"+userId+"' group by venue_id;";
    		finalReport = statement.executeQuery(query1);
    		
    		if(finalReport.next()){
        		System.out.println(suggestList.get(i).getVenueId()+" : +++++"); 
        		suggestList.get(i).setVisited(true);
    		}else{
    			System.out.println(suggestList.get(i).getVenueId()+" : -----");
    			suggestList.get(i).setVisited(false);
    		}
    		
    		returnedList.add(suggestList.get(i));
			
			}
		return returnedList;
		
	}
	public List<VenueModel> SuggestVenueByUserSimilarity(String userId,String user1,String user2,String user3) throws Exception
	{
		
		List<VenueModel> venuelist = new ArrayList<VenueModel>();
		List<VenueModel> sortedList = new ArrayList<VenueModel>();
		Statement statement = dbConnection.getConn();
		Statement statement2 = dbConnection.getConn();
		
		//ResultSet uservenues=null;
		ResultSet countcheckin=null;
		ResultSet notVisitedVenues=null;
		//uservenues=statement.executeQuery("select venue_id from checkintrain where user_id='"+userId +"' group by venue_id");
		//otherusersvenues=statement2.executeQuery("select venue_id from checkintrain where user_id='"+user1 +"' or user_id='"+user2+"' or user_id='"+user3+"'  group by venue_id");
		//String getNotVisitedVenues="select venue_id from checkintrain where (user_id='"+user1+"' or user_id='"+user2+"' or user_id='"+user3+"') and venue_id  not in(select venue_id from checkintrain where user_id='"+userId+"' group by venue_id ) group by venue_id;";
		//String getNotVisitedVenues="select venue_id from checkintrain where (user_id='"+user1+"' or user_id='"+user2+"' or user_id='"+user3+"')  group by venue_id;";
		String getNotVisitedVenues="select venue_id from checkintrain where (user_id='"+user1+"' or user_id='"+user2+"' or user_id='"+user3+"' )  group by venue_id;";
		notVisitedVenues= statement.executeQuery(getNotVisitedVenues);
		 while(notVisitedVenues.next())
   	  {
   	  VenueModel venue = new VenueModel();
			String getCheckinCount="Select count(*) from checkintrain where (user_id='"+user1+"' or user_id='"+user2+"' or user_id='"+user3+"') and venue_id='"+notVisitedVenues.getString(1) +"'"; 
			countcheckin =statement2.executeQuery(getCheckinCount);// herbir mekanın checkin sayısı bulundu
			//checkin sayısı en yüksek olan 5 mekan kullanıcıya önerilecek id leri bir dizide saklanacak
			countcheckin.next();			
			venue.setCheckinCount(countcheckin.getInt(1));
			countcheckin=statement2.executeQuery("Select Latitude,Longitude,NeighborhoodCode from PolsUpdated where VenueId='"+notVisitedVenues.getString(1)+"'");
			countcheckin.next();
			venue.setVenueId(notVisitedVenues.getString(1));
			venue.setLatitude(countcheckin.getString(1));
			venue.setLongitude(countcheckin.getString(2));
			venue.setNeighborhoodCode(countcheckin.getString(3));
			venuelist.add(venue);
   	  }
		
		QuickSort quick = new QuickSort();
		sortedList=quick.sort(venuelist);
		
		
		return sortedList;
	}
	
	public int compareTwoUser(String userId,String user2Id) throws Exception
	{
		int count=0;
		Statement statement = dbConnection.getConn();
		Statement statement2 = dbConnection.getConn();
		ResultSet uservenues=null;
		ResultSet user2venues=null;
		uservenues=statement.executeQuery("select venue_id from checkintrain where user_id='"+userId +"' group by venue_id");
		user2venues=statement2.executeQuery("select venue_id from checkintrain where user_id='"+user2Id +"' group by venue_id");
		while(uservenues.next())
		{
			user2venues.absolute(0);
			while(user2venues.next())
			{
				
				if(uservenues.getString(1).equals( user2venues.getString(1)))
				{
					count++;
			        break;
				}
				
			}
			
		}
		return count;
	}
}
