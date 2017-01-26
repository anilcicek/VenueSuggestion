package senior.project;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.Demo;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;


import algorithms.FindHomeLocation;
import algorithms.FindPopularVenueByTestLocation;
import algorithms.MostPopularCategory;
import algorithms.MostPopularCategoryByTestLocation;
import algorithms.UserToUserSimilarity;
import algorithms.VenueToVenueSimilarity;
import algorithms.findpopulervenue;
import connections.DbConnection;
import model.Geocode;
import model.VenueModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainPage {

	private JFrame frmPlacePredctonOf;
	Demo d;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frmPlacePredctonOf.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPlacePredctonOf = new JFrame();
		frmPlacePredctonOf.setTitle("PLACE PREDICTION OF LOCATION BASED SOCIAL \r\nNETWORKING");
		frmPlacePredctonOf.setBounds(100, 100, 869, 573);
		frmPlacePredctonOf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlacePredctonOf.getContentPane().setLayout(null);
		
		
		final JPanel panel = new JPanel();
		panel.setBounds(112, 238, 46, 33);
		frmPlacePredctonOf.getContentPane().add(panel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(230, 25, 157, 20);
		frmPlacePredctonOf.getContentPane().add(comboBox);
		comboBox.addItem("Most Popular Venue");
		comboBox.addItem("Most Popular Category");
		comboBox.addItem("UserToUserSimilarity");
		comboBox.addItem("VenueToVenueSimilarity");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(248, 147, 341, 182);
		frmPlacePredctonOf.getContentPane().add(scrollPane);
		
		final JTextArea textArea_3 = new JTextArea();
		scrollPane.setViewportView(textArea_3);
		textArea_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea_3.setBackground(new Color(153, 255, 204));

		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//JMapViewer j = new JMapViewer();
				//j.setVisible(true);
				textArea_3.append("***********************\n");
				d = new Demo();
				d.setVisible(true);
				d.setSize(530, 530);
				d.setBounds(740, 141, 550, 530);
			   int indexAlg=comboBox.getSelectedIndex();
			   FindPopularVenueByTestLocation fpvbtl = new FindPopularVenueByTestLocation();
			   MostPopularCategoryByTestLocation mpcbtl = new MostPopularCategoryByTestLocation();
			   VenueToVenueSimilarity vtvs = new VenueToVenueSimilarity();
			   UserToUserSimilarity utus = new UserToUserSimilarity();
			   
			   switch(indexAlg){
			   
			  /* case 9:
				   DbConnection dbConnection = new DbConnection();
				   MostPopularCategory mpc = new MostPopularCategory();
				   
				   FindHomeLocation fhl2 = new FindHomeLocation();
				   Geocode konum2=null;
				try {
					konum2 = fhl2.find(textField.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   try {
					   Statement statement = dbConnection.getConn();
						String query="select Latitude,Longitude from PolsUpdated where VenueId in (select venue_id from checkintest where user_id='"+ textField.getText()+"' group by venue_id)";
			         	ResultSet finalReport = statement.executeQuery(query);
			         	while(finalReport.next()){
			         		d.map.addMapMarker(new MapMarkerDot(Color.YELLOW,Double.parseDouble(finalReport.getString(1)),Double.parseDouble(finalReport.getString(2))));
			         	}
			         	//finalReport=statement.executeQuery("select lat,long from usersupdated where user_id='"+textField.getText()+"'");
					    // finalReport.next();   
			         	d.map.addMapMarker(new MapMarkerDot(Color.MAGENTA ,konum2.getLatitude(),konum2.getLongitude()));
			         	List<VenueModel> venuelist =mpc.SuggestPopulerCategoryVenues(textField.getText(),konum2);
					   for(int i=0; i<venuelist.size();i++){
						//   System.out.println(Double.parseDouble(venuelist.get(i).getLatitude())+"  "+Double.parseDouble(venuelist.get(3).getLongitude()));
						   
						   if(venuelist.get(i).isVisited()){
							   d.map.addMapMarker(new MapMarkerDot(Color.GREEN,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
						   }else{
							   d.map.addMapMarker(new MapMarkerDot(Color.RED,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
						   }	   
				    }
					
					   
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   
				   break;*/
			   /*case 8:
				   DbConnection dbConnection2 = new DbConnection();
				   findpopulervenue fpv = new findpopulervenue();
				   FindHomeLocation fhl1 = new FindHomeLocation();
				   Geocode konum1=null;
				try {
					konum1 = fhl1.find(textField.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   try {
					   Statement statement = dbConnection2.getConn();
						String query="select Latitude,Longitude from PolsUpdated where VenueId in (select venue_id from checkintest where user_id='"+ textField.getText()+"' group by venue_id)";
			         	ResultSet finalReport = statement.executeQuery(query);
			         	while(finalReport.next()){
			         		d.map.addMapMarker(new MapMarkerDot(Color.YELLOW,Double.parseDouble(finalReport.getString(1)),Double.parseDouble(finalReport.getString(2))));
			         	}
			         	//finalReport=statement.executeQuery("select lat,long from usersupdated where user_id='"+textField.getText()+"'");
			         	//finalReport.next();   
			         	d.map.addMapMarker(new MapMarkerDot(Color.MAGENTA ,konum1.getLatitude(),konum1.getLongitude()));
					List<VenueModel> venuelist = fpv.findnearpopulervenue(textField.getText(),konum1);
					 for(int i=0; i<venuelist.size();i++){
							//   System.out.println(Double.parseDouble(venuelist.get(i).getLatitude())+"  "+Double.parseDouble(venuelist.get(i).getLongitude()));
							   
							   if(venuelist.get(i).isVisited()){
								   d.map.addMapMarker(new MapMarkerDot(Color.GREEN,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
							   }else{
								   d.map.addMapMarker(new MapMarkerDot(Color.RED,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
							   }	   
					    }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   break;*/
			   
			   case 0:
				   DbConnection dbConnection5 = new DbConnection();
				   FindHomeLocation fhl3 = new FindHomeLocation();
				   Geocode konum3=null;
				try {
					konum3 = fhl3.find(textField.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   
				   try {
					   Statement statement = dbConnection5.getConn();
						String query="select Latitude,Longitude from PolsUpdated where VenueId in (select venue_id from checkintest where user_id='"+ textField.getText()+"' group by venue_id)";
			         	ResultSet finalReport = statement.executeQuery(query);
			         	while(finalReport.next()){
			         		d.map.addMapMarker(new MapMarkerDot(Color.YELLOW,Double.parseDouble(finalReport.getString(1)),Double.parseDouble(finalReport.getString(2))));
			         	}
			         	finalReport=statement.executeQuery("select lat,long from usersupdated where user_id='"+textField.getText()+"'");
					     finalReport.next();   
			         	d.map.addMapMarker(new MapMarkerDot(Color.MAGENTA ,konum3.getLatitude(),konum3.getLongitude()));
					List<VenueModel> venuelist = fpvbtl.SuggestVenuebyTestLocation(textField.getText());
					 for(int i=0; i<venuelist.size();i++){
						textArea_3.append(venuelist.get(i).isVisited()+"     Venue-"+(i+1)+"     " + venuelist.get(i).getNeighborhoodCode()  +"\n");  
							   if(venuelist.get(i).isVisited()){
								   d.map.addMapMarker(new MapMarkerDot(Color.GREEN,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
							   }else{
								   d.map.addMapMarker(new MapMarkerDot(Color.RED,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
							   }	   
					    }
					 
					 	
						
						  d.map.setDisplayToFitMapMarkers();
							panel.add(d);
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   
				   break;
			   case 1:
				   DbConnection dbConnection6 = new DbConnection();
				   FindHomeLocation fhl4 = new FindHomeLocation();
				   Geocode konum4=null;
				try {
					konum4 = fhl4.find(textField.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   try {
					   Statement statement = dbConnection6.getConn();
						String query="select Latitude,Longitude from PolsUpdated where VenueId in (select venue_id from checkintest where user_id='"+ textField.getText()+"' group by venue_id)";
			         	ResultSet finalReport = statement.executeQuery(query);
			         	while(finalReport.next()){
			         		d.map.addMapMarker(new MapMarkerDot(Color.YELLOW,Double.parseDouble(finalReport.getString(1)),Double.parseDouble(finalReport.getString(2))));
			         	}
			         	//finalReport=statement.executeQuery("select lat,long from usersupdated where user_id='"+textField.getText()+"'");
					    // finalReport.next();   
			         	d.map.addMapMarker(new MapMarkerDot(Color.MAGENTA ,konum4.getLatitude(),konum4.getLongitude()));
			         	List<VenueModel> venuelist =mpcbtl.SuggestVenueByTestLocation(textField.getText());
					   for(int i=0; i<venuelist.size();i++){
						//   System.out.println(Double.parseDouble(venuelist.get(i).getLatitude())+"  "+Double.parseDouble(venuelist.get(3).getLongitude()));
						   textArea_3.append(venuelist.get(i).isVisited()+"     Venue-"+(i+1)+"     " + venuelist.get(i).getNeighborhoodCode()  +"\n");
						   
						   if(venuelist.get(i).isVisited()){
							   d.map.addMapMarker(new MapMarkerDot(Color.GREEN,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
						   }else{
							   d.map.addMapMarker(new MapMarkerDot(Color.RED,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
						   }	   
				    }
							   
					   
				
						  d.map.setDisplayToFitMapMarkers();
							panel.add(d);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   break;
			   
			   case 2:
				   DbConnection dbConnection4 = new DbConnection();
				   FindHomeLocation fhl = new FindHomeLocation();
				   Geocode konum=null;
				try {
					konum = fhl.find(textField.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   
				   try {
					   Statement statement = dbConnection4.getConn();
						String query="select Latitude,Longitude from PolsUpdated where VenueId in (select venue_id from checkintest where user_id='"+ textField.getText()+"' group by venue_id)";
			         	ResultSet finalReport = statement.executeQuery(query);
			         	while(finalReport.next()){
			         		d.map.addMapMarker(new MapMarkerDot(Color.YELLOW,Double.parseDouble(finalReport.getString(1)),Double.parseDouble(finalReport.getString(2))));
			         	}
			         	finalReport=statement.executeQuery("select lat,long from usersupdated where user_id='"+textField.getText()+"'");
					     finalReport.next();   
			         	d.map.addMapMarker(new MapMarkerDot(Color.MAGENTA ,konum.getLatitude(),konum.getLongitude()));
					List<VenueModel> venuelist = utus.findNearestUser(textField.getText(),konum);
					 for(int i=0; i<venuelist.size();i++){
							//   System.out.println(Double.parseDouble(venuelist.get(i).getLatitude())+"  "+Double.parseDouble(venuelist.get(i).getLongitude()));
						 textArea_3.append(venuelist.get(i).isVisited()+"     Venue-"+(i+1)+"     " + venuelist.get(i).getNeighborhoodCode()  +"\n");
						 
						 if(venuelist.get(i).isVisited()){
								   d.map.addMapMarker(new MapMarkerDot(Color.GREEN,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
							   }else{
								   d.map.addMapMarker(new MapMarkerDot(Color.RED,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
							   }	   
					    }
					 /*
					  venuelist = vtvs.findSimilarVenues(textField.getText());
					  for(int i=0; i<venuelist.size();i++){
							 textArea_3.append(venuelist.get(i).isVisited()+"     Venue-"+(i+1)+"     " + venuelist.get(i).getNeighborhoodCode()  +"\n");
							   
					   }
					 
					  venuelist =	 mpcbtl.SuggestVenueByTestLocation(textField.getText());
					  for(int i=0; i<venuelist.size();i++){
						 textArea_1.append(venuelist.get(i).isVisited()+"     Venue-"+(i+1)+"     " + venuelist.get(i).getNeighborhoodCode()  +"\n");
						   
					  }
					  
					  venuelist = fpvbtl.SuggestVenuebyTestLocation(textField.getText());
					  for(int i=0; i<venuelist.size();i++){
							 textArea.append(venuelist.get(i).isVisited()+"     Venue-"+(i+1)+"     " + venuelist.get(i).getNeighborhoodCode()  +"\n");
							   
					  }
					  */
					  
					  
					  
					  
					 d.map.setDisplayToFitMapMarkers();
						panel.add(d);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   
				   break;
			   case 3:
				   DbConnection dbConnection3 = new DbConnection();
				   FindHomeLocation fhl5 = new FindHomeLocation();
				   Geocode konum5=null;
					try {
						konum5 = fhl5.find(textField.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   try {
					   Statement statement = dbConnection3.getConn();
						String query="select Latitude,Longitude from PolsUpdated where VenueId in (select venue_id from checkintest where user_id='"+ textField.getText()+"' group by venue_id)";
			         	ResultSet finalReport = statement.executeQuery(query);
			         	while(finalReport.next()){
			         		d.map.addMapMarker(new MapMarkerDot(Color.YELLOW,Double.parseDouble(finalReport.getString(1)),Double.parseDouble(finalReport.getString(2))));
			         	}
			         	finalReport=statement.executeQuery("select lat,long from usersupdated where user_id='"+textField.getText()+"'");
					     finalReport.next();   
			         	d.map.addMapMarker(new MapMarkerDot(Color.MAGENTA ,Double.parseDouble(finalReport.getString(1)),Double.parseDouble(finalReport.getString(2))));
					List<VenueModel> venuelist = vtvs.findSimilarVenues(textField.getText());
					 for(int i=0; i<venuelist.size();i++){
							   System.out.println(Double.parseDouble(venuelist.get(i).getLatitude())+"  "+Double.parseDouble(venuelist.get(i).getLongitude()));
							   String mekan = new String( venuelist.get(i).getNeighborhoodCode().getBytes(),"UTF-8");
							   textArea_3.append(venuelist.get(i).isVisited()+"     Venue-"+(i+1)+"     " +  mekan +"\n");
							   
							   if(venuelist.get(i).isVisited()){
								   d.map.addMapMarker(new MapMarkerDot(Color.GREEN,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
							   }else{
								   d.map.addMapMarker(new MapMarkerDot(Color.RED,Double.parseDouble(venuelist.get(i).getLatitude()),Double.parseDouble(venuelist.get(i).getLongitude())));
							   }	   
					    }
					 
					 
					
						  
						  d.map.setDisplayToFitMapMarkers();
							panel.add(d);
						  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   break;
			   
				   
			   }
			   
			}
		});
		btnNewButton.setBounds(598, 24, 89, 23);
		frmPlacePredctonOf.getContentPane().add(btnNewButton);
		
		
		
		JLabel lblNewLabel = new JLabel("Algorithims");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(131, 27, 89, 17);
		frmPlacePredctonOf.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(477, 25, 86, 20);
		frmPlacePredctonOf.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserId.setBounds(421, 28, 46, 14);
		frmPlacePredctonOf.getContentPane().add(lblUserId);
		
		JLabel lblVenuevenueSmlartyResults = new JLabel("RESULTS");
		lblVenuevenueSmlartyResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblVenuevenueSmlartyResults.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		lblVenuevenueSmlartyResults.setBounds(253, 109, 317, 14);
		frmPlacePredctonOf.getContentPane().add(lblVenuevenueSmlartyResults);
		
		JLabel lblAnliek = new JLabel("ANIL ÇİÇEK ");
		lblAnliek.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnliek.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAnliek.setBounds(512, 488, 317, 14);
		frmPlacePredctonOf.getContentPane().add(lblAnliek);
		
		JLabel lblAlperKrkmez = new JLabel("ALPER KÖRÜKMEZ");
		lblAlperKrkmez.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlperKrkmez.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAlperKrkmez.setBounds(512, 513, 317, 14);
		frmPlacePredctonOf.getContentPane().add(lblAlperKrkmez);
		
		
		
        
        
		
	}
}
