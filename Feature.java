package project2;
/**
 * This class represents a particular feature. 
 * It stores information about the feature's name, class, and location.
 *  
 * Exceptions are handled. 
 * @author Sunny Tao
 */

public class Feature implements Comparable<Feature> {
	
	private String featureName;
	private String featureClass; 
	private Location featureLocation;
	
	/**
	 * Constructs a new Feature object. 
	 * @param featureName name of the feature; 
	 * @param featureClass class of the feature;
	 * @param featureLocation location of the feature
	 * @throws IllegalArgumentException if parameters are invalid.
	 */
	public Feature (String featureName, String featureClass, Location featureLocation) throws IllegalArgumentException{
		if(featureName == null || featureClass == null || featureLocation == null) 
			throw new IllegalArgumentException("Invalid parameters");
		this.featureName = featureName;
		this.featureClass = featureClass;
		this.featureLocation = featureLocation;
	}
	
	/**
	 * Returns the featureName of this Feature object. 
	 * @return the featureName of this Feature object 
	 */
	public String getFeatureName() {
		return featureName;
	}
	
	/**
	 * Returns the featureClass of this Feature object. 
	 * @return the featureClass of this Feature object 
	 */
	public String getFeatureClass() {
		return featureClass;
	}
	
	/**
	 * Returns the featureLocation of this Feature object. 
	 * @return the featureLocation of this Feature object 
	 */
	public Location getFeatureLocation() {
		return featureLocation;
	}
	
	/**
	 * Returns the featureState of this Feature object. 
	 * @return the featureState of this Feature object 
	 */
	public String getFeatureState() {
		return this.getFeatureLocation().getState();
	}
	
	/**
	 * Returns the string representation of this Location.
	 * @returns the string representation of this Location object 
	 */
	@Override
	public String toString () {
		
		return String.format("%s, %s\n%s", 
				featureName , featureClass,featureLocation.toString()) ; 
		
	}
	
	/**
	 * Indicates whether some object obj is "equal to" this one. 
	 * Two Feature objects are considered equal if they have identical names, class, and location.
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Feature))
			return false;
		Feature other = (Feature) obj;
		if (!featureName.equalsIgnoreCase(other.featureName))
			return false;
		if (!featureClass.equalsIgnoreCase(other.featureClass))
			return false;
		if (!featureLocation.equals(other.featureLocation))
			return false;
		
		return true;
	}
	

	/** Compares this object with the specified object for order. 
	 * Two Feature objects should be compared based on their names, location, and class.
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Feature o) {
		if(this.featureName.compareToIgnoreCase(o.featureName)==0) {
			if(this.featureLocation.compareTo(o.featureLocation)==0) {
				if(this.featureClass.compareToIgnoreCase(o.featureClass)==0) {
					return 0;
				} else if(this.featureClass.compareToIgnoreCase(o.featureClass)<0) {
					return -1;
				} else {
					return 1;
				}
			} else if (this.featureLocation.compareTo(o.featureLocation)<0) {
				return -1;
			} else {
				return 1;
			}
		} else if (this.featureName.compareToIgnoreCase(o.featureName)<0) {
			return -1;
		} else {
			return 1;
		}
	}

}