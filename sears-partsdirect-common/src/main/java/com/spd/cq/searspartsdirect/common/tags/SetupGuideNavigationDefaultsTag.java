package com.spd.cq.searspartsdirect.common.tags;

import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spd.cq.searspartsdirect.common.helpers.Constants;

public class SetupGuideNavigationDefaultsTag extends CQBaseTag {
	private final static Logger log = LoggerFactory.getLogger(SetupGuideNavigationDefaultsTag.class);
	@Override
	public int doStartTag() throws JspException {
		try {
			if (!currentNode.hasProperty("sections") || currentNode.getProperty("sections").isNew()) {
				Session jcr = currentNode.getSession();
				currentNode.setProperty("sections",
					new String[]{
						"{\"link\":\""+Constants.PARTS_REQ_DEF_STICKY_LINK+"\",\"resType\":\""+Constants.PARTS_REQ_R_COMPONENT+"\"}",
						"{\"link\":\""+Constants.TOOLS_REQ_DEF_STICKY_LINK+"\",\"resType\":\""+Constants.TOOLS_REQ_R_COMPONENT+"\"}",
						"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.COMMENTS_COMPONENT+"\"}",
						"{\"link\":\""+Constants.EMPTY+"\",\"resType\":\""+Constants.SUBHEAD_COMPONENT+"\"}",
					}
					,PropertyType.STRING);
				jcr.save();
			}
		} catch (RepositoryException re) {
			log.warn("Could not set defaults, ",re);
		}
		return super.doStartTag();
	}
}
