package project2;

/**
 * This class represents a location. 
 * It provides constructor, accessor, and mutator for the Location object.
 *  
 * Exceptions are handled. 
 * @author Sunny Tao
 */

public class Location implements Comparable<Location>{

	private String state;
	private String county; 

	private String latitude;
	private String longitude;
	private String elevation;

	/**
	 * Constructs a new Location object. 
	 * @param state name of the state; 
	 * @param county name of the county;
	 * @throws IllegalArgumentException if parameters are invalid.
	 */

	public Location(String state, String county) throws IllegalArgumentException{
		if(state == null || county == null) 
			throw new IllegalArgumentException("Parameters are invalid.");
		this.state = state;
		this.county = county;
	}

	/**
	 * Returns the latitude of this Location object. 
	 * @return the latitude of this Location object 
	 */
	public double getLatitude(){
		return Double.parseDouble(latitude);
	}

	/**
	 * Validates and sets the latitude for this Location object. 
	 * @param latitude latitude to be examined and set. 
	 * @throws IllegalArgumentException if the latitude is invalid - a valid latitude is in the range of from -90 to +90 (inclusive)
	 */
	public void setLatitude( double latitude ) throws IllegalArgumentException{
		if(latitude>90 || latitude<-90)
			throw new IllegalArgumentException("Invalid latitude. Latitude should be in the range of from -90 to +90");
		this.latitude = String.format("%.6f",latitude);
	}

	/**
	 * Returns the longitude of this Location object. 
	 * @return the longitude of this Location object 
	 */
	public double getLongitude(){
		return Double.parseDouble(longitude);
	}

	/**
	 * Validates and sets the longitude for this Location object. 
	 * @param longitude longitude to be examined and set. 
	 * @throws IllegalArgumentException if the longitude is invalid - a valid longitude is in the range of from -180 to +180 (inclusive)
	 */
	public void setLongitude( double longitude ) throws IllegalArgumentException{
		if(longitude>90 || longitude<-90)
			throw new IllegalArgumentException("Invalid longitude. Longitude should be in the range of from -180 to +180");
		this.longitude = String.format("%.6f",longitude);
	}

	/**
	 * Returns the elevation of this Location object. 
	 * @return the elevation of this Location object 
	 */
	public int getElevation(){
		return Integer.parseInt(elevation);
	}

	/**
	 * Validates and sets the elevation for this Location object. 
	 * @param elevation elevation to be examined and set. 
	 * @throws IllegalArgumentException if the elevation is invalid
	 */
	public void setElevation( int elevation ) throws IllegalArgumentException{
		this.elevation = String.valueOf(elevation);
	}
	
	/**
	 * Returns the state of this Location object. 
	 * @return the state of this Location object 
	 */
	public String getState(){
		return state;
	}

	/**
	 * Returns the string representation of this Location.
	 * @returns the string representation of this Location object 
	 */
	@Override
	public String toString () {
		String lat;
		String longi;
		String ele;
		if (latitude != null && longitude != null && elevation != null) { 
			return String.format("%s, %s\n%s,%s, %s", 
					county , state.toUpperCase(), latitude, longitude ,elevation); 
		}
		else {
			if(latitude == null){
				lat = "0";
			} else {
				lat = latitude;
			}
			if(longitude == null){
				longi = "0";
			}else {
				longi = longitude;
			}
			if(elevation == null){
				ele = "0";
			} else {
				ele = elevation;
			}
			return String.format("%s, %s\n%s,%s, %s", 
					county , state.toUpperCase(), lat, longi ,ele); 
		}
	}
	
	/**
	 * Indicates whether some object obj is "equal to" this one. 
	 * Two Location objects are considered equal if their state, county, latitude, longitude, 
	 * and elevation values are the same 
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Location))
			return false;
		Location other = (Location) obj;
		if (!state.equalsIgnoreCase(other.state))
			return false;
		if (!county.equalsIgnoreCase(other.county))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (elevation == null) {
			if (other.elevation != null)
				return false;
		} else if (!elevation.equals(other.elevation))
			return false;
		return true;
	}

	/** Compares this object with the specified object for order. 
	 * Two Location objects should be compared based on their state names, county names, latitude, longitude, and elevation.
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Location o) {
		if(this.state.compareToIgnoreCase(o.state)==0) {
			if(this.county.compareToIgnoreCase(o.county)==0) {
				if(this.latitude.compareTo(o.latitude)==0) {
					if(this.longitude.compareTo(o.longitude)==0) {
						if(this.elevation.compareTo(o.elevation)==0) {
							return 0;
						} else if (this.elevation.compareTo(o.elevation)<0) {
							return -1;
						} else {
							return 1;
						}
					} else if(this.longitude.compareTo(o.longitude)<0) {
						return -1;
					} else {
						return 1;
					}
				} else if(this.latitude.compareTo(o.latitude)<0) {
					return -1;
				} else {
					return 1;
				}
			} else if(this.county.compareToIgnoreCase(o.county)<0) {
				return -1;
			} else {
				return 1;
			}
		} else if(this.state.compareToIgnoreCase(o.state)==0) {
			return -1;
		} else {
			return 1;
		}
	}

}