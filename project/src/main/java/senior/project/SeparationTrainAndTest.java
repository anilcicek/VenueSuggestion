package senior.project;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.introspect.BasicClassIntrospector.GetterMethodFilter;

import algorithms.FindHomeLocation;
import algorithms.findpopulervenue;
import connections.DbConnection;
import model.Geocode;
import model.VenueModel;
import model.DateFormat;
public class SeparationTrainAndTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbConnection dbConnection = new DbConnection();
		try{
			
			Statement statement = dbConnection.getConn();
			ResultSet userids =null;
			String getuserids="select user_id, count(*) from checkinlast group by user_id";
			userids=statement.executeQuery(getuserids);
			Statement statement2 = dbConnection.getConn();
			ResultSet usercheckins =null;
			String getusercheckins="";
			int j,i,size;
			Statement statement3 = dbConnection.getConn();
			String insertcheckin="";
			j=1;
			while(userids.next())
			{
				getusercheckins="select * from checkinlast where user_id='"+userids.getString(1) +"' order by time asc";
				usercheckins=statement2.executeQuery(getusercheckins);
				i=1;
				size=(userids.getInt(2)*80)/100;
				while(usercheckins.next()   )
				{
					if(i<size)
					{
						insertcheckin="Insert into checkintrain (user_id,venue_id,time,timezone ) values ('"+usercheckins.getString(1) +" ','"+ usercheckins.getString(2)+"', '"+usercheckins.getString(3) +"','"+usercheckins.getString(4)+"' )";
						statement3.execute(insertcheckin);
					}
					else
					{
						insertcheckin="Insert into checkintest (user_id,venue_id,time,timezone ) values ('"+usercheckins.getString(1) +" ','"+ usercheckins.getString(2)+"', '"+usercheckins.getString(3) +"','"+usercheckins.getString(4)+"' )";
						statement3.execute(insertcheckin);
					}
					
					i++;
				}
				System.out.println(j);
				j++;
			}
			
			
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		

	}

}
