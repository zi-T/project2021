package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class is the execution of the program Every Place Has a Name. 
 * This program is interactive. 
 * The user enters the names as required in the instructions given in the console. 
 *
 * @author Sunny Tao
 */

public class EveryPlaceHasAName{
    
	/**
	 * Splits the given line of a pipe-delimited file according to | characters.
	 * @author Joanna Klukowska
	 * @param textLine	a line of text to be parsed
	 * @return the array containing words (or empty strings) from between | characters
	 */
	public static String [] splitInputLine(String textLine){

		if (textLine == null ) return null;

		String [] entries = null;

		entries = textLine.split("\\|");

		return entries;
	}
	
	/**
	 * Finds the index of a string in the array
	 * @author Sunny Tao
	 * @param arr the String array; word the String to be found.
	 * @return int index
	 */
	public static int getArrayIndex(String[] arr,String word) {
        int k=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i].equals(word)){
                k=i;
                break;
            }
        }
    	return k;
	}
	
	/**
	 * The main() method of this program. 
	 * @param args array of Strings provided on the command line when the program is started; 
	 * the first string should be the name of the input file containing the places. 
     * System.err reference project 1.
	 */
     public static void main(String[] args){
        //verify that the command line argument exists 
		if (args.length == 0 ) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
		
		//verify that command line argument contains a name of an existing file 
		File locFile = new File(args[0]); 
		if (!locFile.exists()){
			System.err.println("Error: the file "+locFile.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!locFile.canRead()){
			System.err.println("Error: the file "+locFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}

        //open the file for reading
        Scanner inLoc = null;
        try {
			inLoc = new Scanner (locFile ) ;
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file "+locFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}

        
        //read the content of the file and save the data in a list
		FeatureList list = new FeatureList(); 
        String line = null; 
		Scanner parseLine = null; 
		String[] lineArr = null;
		String state = null;
		String county = null; 
		String featureName = null;
		String featureClass = null;
		double latitude = 0;
		double longitude = 0;
		int elevation = 0;
		Location currentL = null;
		Feature currentF = null;
		int istate, icounty, ifC, ifN, ilat, ilong, iele;
		istate=icounty=ifC=ifN=ilat=ilong=iele =0;
		int n =0;
        while(inLoc.hasNextLine()) {
        	try{
        		line = inLoc.nextLine(); 
        		lineArr = splitInputLine(line);
				if(n==0){
					//find each index of the categories
					istate = getArrayIndex(lineArr,"STATE_ALPHA");
					icounty = getArrayIndex(lineArr,"COUNTY_NAME");
					ifN = getArrayIndex(lineArr,"FEATURE_NAME");
					ifC = getArrayIndex(lineArr,"FEATURE_CLASS");
					ilat = getArrayIndex(lineArr,"PRIM_LAT_DEC");
					ilong = getArrayIndex(lineArr,"PRIM_LONG_DEC");
					iele = getArrayIndex(lineArr,"ELEV_IN_FT");
				}
				
        		if(n!=0) {
        			//find the actual values of each field
					try {
        				state = lineArr[istate];
        			} catch (NoSuchElementException ex) {
        				continue;
        			}
					try {
        				county = lineArr[icounty];
        			} catch (NoSuchElementException ex) {
        				continue;
        			}
					try {
        				featureName = lineArr[ifN];
        			} catch (NoSuchElementException ex) {
        				continue;
        			}
					try {
        				featureClass = lineArr[ifC];
        			} catch (NoSuchElementException ex) {
        				continue;
        			}
        			try {
        				latitude = Double.parseDouble(lineArr[ilat]);
        			} catch (NoSuchElementException ex) {
        				//skips
        			}
        			try {
        				longitude = Double.parseDouble(lineArr[ilong]);
        			} catch (NoSuchElementException ex) {
        				//skips
        			}
        			try {
        				elevation = Integer.parseInt(lineArr[iele]);
        			} catch (NoSuchElementException ex) {
        				//skips
        			}
        		}
        	} catch(NoSuchElementException ex) {
        		continue;
        	}
        	try {
        		//create Location object
				currentL = new Location (state, county);
				if(latitude != 0) {
					currentL.setLatitude(latitude);
				} 
				if(longitude != 0) {
					currentL.setLongitude(longitude);
				}
				if(elevation != 0) {
					currentL.setElevation(elevation);
				}
			}
			catch (IllegalArgumentException ex ) {
				//skips
			}
        	try {
        		//create Feature object
				currentF = new Feature (featureName, featureClass,currentL);
				list.add(  currentF  ); 
			}
			catch (IllegalArgumentException ex ) {
				//skips
			}
        	n++;
        }
        
        //interactive mode: reference project 1

        Scanner userInput  = new Scanner (System.in ); 
        String userValue = "";
        
        System.out.println("Search the dataset by using one of the following queries.\n"
        		+ "  To search for features by keyword in their name, enter\n"
        		+ "	name KEYWORD\n"
        		+ "  To limit the search to a particular class of features , enter\n"
        		+ "	name KEYWORD class FEATURE_CLASS\n"
        		+ "  To limit the search to a particular state, enter\n"
        		+ "	name KEYWORD state STATE\n"
        		+ "  Or combine both restrictions by entering\n"
        		+ "	name KEYWORD class CLASS state STATE\n"
        		+ "    or\n"
        		+ "	name KEYWORD state STATE class CLASS\n"
        		+ "    To terminate the program, enter\n"
        		+ "	quit");
        
        FeatureList fl1;
        FeatureList fl2;
        FeatureList fl3;

        do {
        	System.out.println("Enter your search query: " );
        	//get value of from the user 
        	userValue = userInput.nextLine();
        	if (!userValue.equalsIgnoreCase("quit")) { 
        		//the situation when user inputs three categories
        		if(userValue.contains("name")&& userValue.contains("state") && userValue.contains("class")) {
        			if(userValue.indexOf("class")<userValue.indexOf("state")) {

        				fl1 = list.getByName(userValue.substring(5,userValue.indexOf("class")-1));
        			} else {
        				fl1 = list.getByName(userValue.substring(5,userValue.indexOf("state")-1));
        			}
        			if(userValue.indexOf("class")<userValue.indexOf("state")) {

        				fl2 = list.getByState(userValue.substring(userValue.indexOf("state")+6,userValue.length()));
        			} else {
        				fl2 = list.getByState(userValue.substring(userValue.indexOf("state")+6,userValue.indexOf("class")-1));
        			}
        			if(userValue.indexOf("class")<userValue.indexOf("state")) {

        				fl3 = list.getByClass(userValue.substring(userValue.indexOf("class")+6,userValue.indexOf("state")-1));

        			} else {
        				fl3 = list.getByClass(userValue.substring(userValue.indexOf("class")+6,userValue.length()));
        			}
        			//check if the three FeatureLists have same Features, and print those that are equal.
        			if(fl1!=null && fl2!=null && fl3!=null) {
        				for(Feature f1 : fl1) {
            				for(Feature f2 : fl2) {
            					for(Feature f3 : fl3) {
            						if(f1.equals(f2) && f2.equals(f3)) {
            							System.out.println(f1.toString()+"\n\n_____");
            						}
            					}
            				}
            			}
        			} else {
        				System.out.println("No matches found. Try again.");
        				continue;
        			}
        			
        			//situation when user enters two categories
        		} else if(userValue.contains("name")&& userValue.contains("state") ) {
        			fl1 = list.getByName(userValue.substring(5,userValue.indexOf("state")-1));
        			fl2 = list.getByState(userValue.substring(userValue.indexOf("state")+6,userValue.length()));
        			if(fl1!=null && fl2!=null) {
        				for(Feature f1 : fl1) {
            				for(Feature f2 : fl2) {
            					if(f1.equals(f2)) {
            						System.out.println(f1.toString()+"\n\n_____");
            					}
            				}
            			}
        			} else {
        				System.out.println("No matches found. Try again.");
        				continue;
        			}
        			
        			//situation when user enters two categories
        		}else if(userValue.contains("name")&& userValue.contains("class") ) {
        			fl1 = list.getByName(userValue.substring(5,userValue.indexOf("class")-1));
        			fl3 = list.getByClass(userValue.substring(userValue.indexOf("class")+6,userValue.length()));
        			if(fl1!=null && fl3!=null) {
        				for(Feature f1 : fl1) {
            				for(Feature f3 : fl3) {
            					if(f1.equals(f3)) {
            						System.out.println(f1.toString()+"\n\n_____");
            					}
            				}
            			}
        			} else {
        				System.out.println("No matches found. Try again.");
        				continue;
        			}
        			
        			//situation when user enters only name
        		} else if(userValue.contains("name")) {
        			fl1 = list.getByName(userValue.substring(5,userValue.length()));
        			if(fl1!=null) {
        				for(Feature f1 : fl1) {
        					System.out.println(f1.toString()+"\n\n_____");
        				}
        			} else {
        				System.out.println("No matches found. Try again.");
        				continue;
        			}
        		} else {
        			System.out.println("This is not a valid query. Try again.");
    				continue;
        		}
        	}

        } while (!userValue.equalsIgnoreCase("quit"));   

        userInput.close();
     }
}