package project2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * FeatureList class is used to store a collection of Feature objects. 
 * This class inherits all of its properties from an ArrayList<Feature>. 
 * It has methods that allow search by names, classes, and states.
 * 
 * SuppressWarnings added due to warning "The serializable class FeatureList does not declare a static final serialVersionUID field of type long".
 * 
 * @author Sunny Tao
 *
 */
@SuppressWarnings("serial")

public class FeatureList extends ArrayList<Feature>{
	
	/**
	 * default constructor
	 */
	//FeatureList features;
	public FeatureList() {}
	
	/**
	 * Returns a sorted list of all Feature objects for which the names contain the keyword.
	 * @param keyword the name to be compared.
	 * @return returns a list of all Feature objects wanted.
	 * @throws IllegalArgumentException if keyword is null or empty
	 */
	public FeatureList getByName(String keyword) throws IllegalArgumentException {
		if(keyword == null || keyword == "") 
			throw new IllegalArgumentException("Invalid keyword");
		FeatureList features = new FeatureList();
		for(Feature f : this) {
			String featureName = f.getFeatureName().toUpperCase();
			keyword = keyword.toUpperCase();
			if(featureName != null && featureName.contains(keyword)) {
				features.add(f);
			}
		}
		if(features.isEmpty()) {
			return null;
		}
		Collections.sort(features);
		return features;
	}
	
	/**
	 * Returns a sorted list of all Feature objects for which the class names contain the keyword.
	 * @param keyword the class name to be compared.
	 * @return returns a list of all Feature objects wanted.
	 * @throws IllegalArgumentException if keyword is null or empty
	 */
	public FeatureList getByClass(String keyword) throws IllegalArgumentException {
		if(keyword == null || keyword == "") 
			throw new IllegalArgumentException("Invalid keyword");
		FeatureList features = new FeatureList();
		for(Feature f : this) {
			String featureClass = f.getFeatureClass().toUpperCase();
			keyword = keyword.toUpperCase();
			if(featureClass != null && featureClass.contains(keyword)) {
				features.add(f);
			}
		}
		if(features.isEmpty()) {
			return null;
		}
		Collections.sort(features);
		return features;
	}
	
	/**
	 * Returns a sorted list of all Feature objects for which the state names contain the state.
	 * @param state the state name to be compared.
	 * @return returns a list of all Feature objects wanted.
	 * @throws IllegalArgumentException if state is null or empty
	 */
	public FeatureList getByState(String state) throws IllegalArgumentException {
		if(state == null || state == "") 
			throw new IllegalArgumentException("Invalid state");
		FeatureList features = new FeatureList();
		for(Feature f : this) {
			String featureState = f.getFeatureState();
			state = state.toUpperCase();
			if(featureState != null && featureState.equals(state)) {
				features.add(f);
			}
		}
		if(features.isEmpty()) {
			return null;
		}
		Collections.sort(features);
		return features;
	}
	
}