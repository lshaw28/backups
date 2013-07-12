package com.spd.cq.searspartsdirect.common.helpers;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides some useful methods for dealing with paths. Instances of this class have no state
 * and all methods are idempotent - this can be held in servlet instance variables.
 * @author bzethmayr
 *
 */
public class PathStringUtils {
	protected static final Logger log = LoggerFactory.getLogger(PathStringUtils.class);
	
	/**
	 * Returns true if the path given is among or has any parent among the given roots.
	 * @param roots The roots within which to check
	 * @param path The path to check
	 * @return true if path is or is under any of the roots
	 */
	public boolean hasAnyParentIn(final Set<String> roots, String path) {
    	boolean hasParent = false;
    	// StringBuilder, would be an allocation for each set check but no realloc for truncation, and calls
    	// String, would be no alloc for set check and alloc for truncation, calls implicit.
    	// String wins on readability. Alt, would be, StringBuilders as the set type. 
    	// See CharSequence docs (in particular, as regards equals and hashCode contracts) for why we don't use it.
    	while (path.length() > 0) {
    		if (roots.contains(path)) {
    			hasParent = true;
    			break;
    		}
    		int slashIdx = path.lastIndexOf("/");
    		path = path.substring(0,slashIdx > 0?slashIdx:0);
    	}
    	return hasParent;
    }
	
	public Set<String> pathsSetFromCsv(final String csvPaths) {
		Set<String> pathSet = new HashSet<String>();
		String[] pathsArr = csvPaths.split("[,;: \t]+");
		for (int i = 0; i < pathsArr.length; i++) {
			if (pathsArr[i].length() > 0) {
				pathSet.add(pathsArr[i]);
			}
		}
		return pathSet;
	}
}
