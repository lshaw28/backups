package com.spd.cq.searspartsdirect.common.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author bzethmayr
 * adapted from LogoCarouselHelper, @author aalkhafaji
 */
public class CarouselHelper {
	protected final static Logger log = LoggerFactory.getLogger(CarouselHelper.class);
	/**
    *
    * @param currentNode   The current node passed in.
    * @param properties    The Value Map of the properties of this node
    * @return ArrayList of logo names.
    */
   public static List<String> getImageList(Node currentNode) {

       List<String> logos = new ArrayList<String>();
       try {
           if (currentNode.hasProperty("primary")) {
        	   Property primaryProp = currentNode.getProperty("primary");
               if (primaryProp.isMultiple()) {
                   Value[] primary = primaryProp.getValues();
                   for (int i = 0; i < primary.length; i++) {
                       String temp = primary[i].getString();
                       String imageName = getFirstKeyStringValueFromJsonObject(temp);
                       if (imageName != null) {
                    	   logos.add(imageName);
                       }
                   }
               } else {
                   String primaryString = primaryProp.getString();
                   String imageName = getFirstKeyStringValueFromJsonObject(primaryString);
        		   if (imageName != null) {
                	   logos.add(imageName);
                   }
               }

           }
       } catch (RepositoryException ex) {
           log.error("Retrieving carousel image list, ",ex);
       }
       return logos;

   }
   
	private static String getFirstKeyStringValueFromJsonObject(String jsonObjectString) {
		String[] temp2 = jsonObjectString.split(":");
		if (temp2.length > 1) {
			String temp3 = temp2[1].substring(1);
			String temp4 = temp3.substring(0, temp3.indexOf("\"")).trim();
			if (temp4.length() > 0) {
				return temp4;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}