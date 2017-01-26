package senior.project;

import java.sql.ResultSet;
import java.sql.Statement;

import connections.DbConnection;

public class VenueSimilarityCalculate {

	public static void main(String[] args) throws Exception {
		DbConnection dbConnection = new DbConnection();
		Statement statement = dbConnection.getConn();
		
		ResultSet res = statement.executeQuery("select c1.venue_id, c2.venue_id, count(*) as sayi from checkintrain c1, checkintrain c2 where c1.user_id = c2.user_id and c1.venue_id = '4c06363a5753c928a4823af1' group by c1.venue_id, c2.venue_id order by sayi desc;");
		while(res.next()){
			
		}

	}

}
