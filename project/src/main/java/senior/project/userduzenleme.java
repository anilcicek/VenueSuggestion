package senior.project;

import java.sql.ResultSet;
import java.sql.Statement;

import algorithms.FindHomeLocation;
import connections.DbConnection;
import model.Geocode;

public class userduzenleme {

	public static void main(String[] args) throws Exception {

    	DbConnection dbConnection = new DbConnection();
        FindHomeLocation fhl = new FindHomeLocation();
        Statement statement1 = dbConnection.getConn();
		Statement statement2 = dbConnection.getConn();
		ResultSet users = statement2.executeQuery("Select user_id from usersupdated");
		int i=1;
		while(users.next())
		{

			Geocode konum = fhl.find(users.getString(1));
		    statement1.execute("Insert into usersupdatedlast (user_id,lat,long) values('"+users.getString(1)+"','"+konum.getLatitude()+"','"+konum.getLongitude()+"') ");
			i++;
			System.out.println(i);
		}
		
		
	}

}
