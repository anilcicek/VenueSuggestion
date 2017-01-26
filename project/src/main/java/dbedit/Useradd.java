package dbedit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Useradd {

	public static void main(String[] args) throws ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=SeniorProject;integratedSecurity=true";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		PreparedStatement prepsInsertProduct = null;
		 String selectSql = "select user_id from dbo.checkinist group by user_id;";  
		 Statement statement = null;
		 //ReverseGeoCode reverseGeoCode = new ReverseGeoCode(new FileInputStream("E:\\Indirmeler\\TR\\TR.txt"), true);
		// ReverseGeoCode reverseGeoCodeCity = new ReverseGeoCode(new FileInputStream("E:\\Indirmeler\\cities5000\\cities5000.txt"), true);
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//statement2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
		 ResultSet resultSet=null;
		try {
			resultSet = statement.executeQuery(selectSql);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 try {
			while (resultSet.next())   
			 {  
				        	 String insertSql = "INSERT INTO dbo.users(user_id) VALUES('"+resultSet.getString(1)+"');";
				//String insertSql = "fds";
				System.out.println(resultSet.getString(1));
								try {
									prepsInsertProduct = conn.prepareStatement(  
									     insertSql,  
									     Statement.RETURN_GENERATED_KEYS);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}  
						         try {
									prepsInsertProduct.execute();
									//resSet = prepsInsertProduct.getGeneratedKeys(); 
						               
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				         
					         
			    
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}

}