package model;

public class VenueModel {

	String venueId;
	String latitude;
	String longitude;
	int checkinCount;
	boolean visited;
	String NeighborhoodCode;
	public String getVenueId() {
		return venueId;
	}
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getCheckinCount() {
		return checkinCount;
	}
	public void setCheckinCount(int checkinCount) {
		this.checkinCount = checkinCount;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public String getNeighborhoodCode() {
		return NeighborhoodCode;
	}
	public void setNeighborhoodCode(String neighborhoodCode) {
		NeighborhoodCode = neighborhoodCode;
	}
}
