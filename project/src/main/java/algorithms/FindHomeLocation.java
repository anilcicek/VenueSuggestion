package algorithms;

import java.sql.ResultSet;
import java.sql.Statement;

import connections.DbConnection;
import model.Geocode;

public class FindHomeLocation {
	DbConnection dbConnection = new DbConnection();
	public Geocode find(String userId) throws Exception{
		Statement statement2 = dbConnection.getConn();
		Statement statement3 = dbConnection.getConn();
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		Double sum_lat = 0.0;
		Double sum_long = 0.0;
		int total_count = 0;
			String sql_1 = "select top 1 venue_id, count(*) from dbo.checkintrain where user_id = '"+userId+"' group by venue_id order by count(*) desc;";
			resultSet2 = statement2.executeQuery(sql_1);
			while(resultSet2.next()){
				String sql_2 = "select Latitude, Longitude from dbo.PolsUpdated where VenueId = '" +resultSet2.getString(1)+"';";
				resultSet3 = statement3.executeQuery(sql_2);
				total_count+=resultSet2.getInt(2);
				
				while(resultSet3.next()){
					//System.out.println(resultSet2.getString(1));
					sum_lat=sum_lat+(resultSet3.getDouble(1)*resultSet2.getInt(2));
					sum_long+=resultSet3.getDouble(2)*resultSet2.getInt(2);
				}
			}
			sum_lat=sum_lat/total_count;
			sum_long=sum_long/total_count;
			Geocode konum = new Geocode();
			konum.setLatitude(sum_lat);
			konum.setLongitude(sum_long);
            
			//System.out.println("new lat: "+sum_lat+" new long: "+sum_long);
		return konum;
	}

}
