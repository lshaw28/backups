package com.spd.cq.searspartsdirect.common.helpers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.jcr.AccessDeniedException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.jcr.version.VersionException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//JIJU :: /etc/spdAssets/scaffolding/productCategory
/*
 0 * * * * ?
 on 0 second every minute, every day, every hour… you get the idea.
 0 15 12 * * ?
 on 0 second, of 15th minute, of 12th hour, every day…. at 12:15 trigger event.
 0 0-5 10,12,2 ? JAN,APR,JUL,OCT 2012-2020
 on 0 second, of 0 minute through 5th minute, at 10am 12noon AND 2pm, everyday, in January, April, July AND October, from 2012 until 2020.
 */

@Component(immediate = false, metatype = true, label = "SiteMap", description = "Component returns sitemap xml")
@Service(value = { java.lang.Runnable.class, CategorySiteMapHelper.class })
@Properties({
        @Property(name = "scheduler.expression", label = "Schedular expression", value = "", description = "specify cron expression"),
        // @Property(name = "scheduler.period", longValue = 120, description =
        // "The time in seconds between task execution, default is 1 hr."),
        @Property(name = "scheduler.concurrent", label = "run concurrent jobs?", boolValue = false, description = "Run jobs concurrently, default is false.") })
// @Property(name = "scheduler.expression", value = "0 15 4 * * ?", description
public class CategorySiteMapHelper implements Runnable {

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    private static final Logger log = LoggerFactory
            .getLogger(CategorySiteMapHelper.class);

    protected ComponentContext context = null;

    protected void activate(ComponentContext context) throws Exception {
//        log.info("PartsDirect SettingsHelper : activate() is called.");
        this.context = context;
    }

    protected void deactivate(ComponentContext context) {
//        log.info("PartsDirect SettingsHelper : deactivate() is called.");
    }

    public void run() {
        try {
            List<String> categoryList = getJCRCategories();
            List<String> brandList = getJCRBrands();
            deleteCreateSiteMapNode();
            createSiteMap(categoryList,brandList);
        } catch (ValueFormatException e) {
            e.printStackTrace();
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void deleteCreateSiteMapNode() throws LoginException,
            AccessDeniedException, PathNotFoundException, VersionException,
            LockException, ConstraintViolationException, RepositoryException {
        String destinationNodePath = "var/pdsitemap";
        ResourceResolver resourceResolver = null;
        resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
        Session session = resourceResolver.adaptTo(Session.class);
        Node rootNode = session.getRootNode();
        if (rootNode.hasNode(destinationNodePath)) {
            Node siteMapNode = rootNode.getNode(destinationNodePath);
            siteMapNode.remove();
        }
        rootNode.addNode(destinationNodePath, "sling:Folder");
        session.save();

    }

    private static String CATEGORY_PATH = "/content/searspartsdirect/en/categories";

    public List<String> getJCRCategories() throws LoginException {

        List<String> categoryList = new ArrayList<String>();
        // Node currentNode = getCurrentNode();
        ResourceResolver resourceResolver = null;
        resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
        Session session = resourceResolver.adaptTo(Session.class);

        String searchString = "SELECT * FROM [cq:PageContent] WHERE ISDESCENDANTNODE([/content/searspartsdirect/en/categories/]) "
                + "and contains('cq:template','/apps/searspartsdirect/templates/category')";

        try {
//            log.info("getJCRCategories()");

            Query query = session.getWorkspace().getQueryManager()
                    .createQuery(searchString, Query.JCR_SQL2);
            QueryResult results = query.execute();

            NodeIterator it = results.getNodes();
            while (it.hasNext()) {
                Node node = it.nextNode();
                String category = node.getProperty("jcr:title").getString();
                // log.info("getJCRCategories():step 4 : category " + category);
                categoryList.add(category.trim());
            }

        } catch (Exception e) {
            log.error("Error occured in CategorySiteMapHelper:getJCRCategories() "
                    + e.getMessage() + " Exception: ");
        }

        return categoryList;

    }

    public Set<String> getJCRSymptoms(String category) throws LoginException {

        Set<String> symptoms = new HashSet<String>();
        // Node currentNode = getCurrentNode();
        ResourceResolver resourceResolver = null;
        resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
        Session session = resourceResolver.adaptTo(Session.class);
        String searchString = "SELECT * FROM [cq:PageContent] WHERE ISDESCENDANTNODE([/etc/spdAssets/scaffolding/symptom/"+ category.toLowerCase() + "/])";
//        log.info("rekkkkk:" + searchString);
        try {
            log.info("getJCRSymptoms for:step1 " + category);

            Query query = session.getWorkspace().getQueryManager()
                    .createQuery(searchString, Query.JCR_SQL2);
            QueryResult results = query.execute();
            NodeIterator it = results.getNodes();
            while (it.hasNext()) {
                Node node = it.nextNode();
                String symptom = node.getProperty("id").getString();
//                log.info("getJCRSymptoms: Category and title: step2 "+ category + " " + symptom);
                symptoms.add(symptom);
            }

        } catch (Exception e) {
            log.error("Error occured in CategorySiteMapHelper:getJCRSymptoms() "+ e.getMessage() + " Exception: ");
        }
        log.info("getJCRSymptoms(); "+symptoms);
        return symptoms;

    }
    
    public List<String> getJCRBrands() throws LoginException {

        List<String> brands = new ArrayList<String>();
        // Node currentNode = getCurrentNode();
        ResourceResolver resourceResolver = null;
        resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
        Session session = resourceResolver.adaptTo(Session.class);

        String searchString = "SELECT * FROM [cq:PageContent] WHERE ISDESCENDANTNODE([/etc/spdAssets/scaffolding/brand/]) and"
                + " contains('cq:scaffolding','/etc/scaffolding/brand')";
//        log.info("brand Query:" + searchString);
        try {
            Query query = session.getWorkspace().getQueryManager().createQuery(searchString, Query.JCR_SQL2);
            QueryResult results = query.execute();
            NodeIterator it = results.getNodes();
            while (it.hasNext()) {
                Node node = it.nextNode();
                String brand = node.getProperty("jcr:title").getString();
//                log.info("getJCRSymptoms: Category and title: step2 "+ category + " " + symptom);
                brands.add(brand);
            }

        } catch (Exception e) {
            log.error("Error occured in CategorySiteMapHelper:getJCRSymptoms() "+ e.getMessage() + " Exception: ");
        }
        log.info("getJCRBrands(); "+brands);
        return brands;

    }


    public void createSiteMap(List<String> categoryList, List<String> brandList)
            throws ValueFormatException, PathNotFoundException,
            UnsupportedEncodingException, JSONException, RepositoryException,
            LoginException {
        StringBuffer sitemapEntryStr = new StringBuffer();
        int fileIndex = 0;
        // log.info("Category : " + categoryList.size());

        for (String categoryStr : categoryList) {
           if (categoryStr.equals("Range")) {
            JSONObject jsonObject = new JSONObject();
            Set<String> cqSymptoms = getJCRSymptoms(categoryStr.trim().replace(" ", "-"));

            parseAPIResponse(jsonObject, categoryStr, 0, 0);
            Set<String> pdSymptoms = getPdSymptoms(jsonObject);
            List jsonArrayList = new ArrayList();
            jsonArrayList.add(retrieveJSONArray(jsonObject));
            jsonArrayList = checkforMoreData(jsonObject, jsonArrayList,categoryStr);

            // jsonObject.put("models", allModels);
            if (isCategoryWithSymptoms(cqSymptoms,pdSymptoms)) {
                sitemapEntryStr.append(createSiteMapURLs(jsonArrayList,brandList));
                saveSitemapURL(sitemapEntryStr, categoryStr);
                log.info("appending sitemaps for " + categoryStr);
            }
            fileIndex++;
            sitemapEntryStr.delete(0, sitemapEntryStr.length());
            }
    }
    }

    private Set<String> getPdSymptoms(JSONObject jsonObject) throws JSONException {
        String pdSymptoms = "";
       
        JSONObject tmpObj = new JSONObject(jsonObject.getString("jsonCategoryDetails"));
        if (tmpObj.has("symptomCodes")) {
            pdSymptoms = (String)tmpObj.get("symptomCodes");
        }
        Set<String> symptoms = new HashSet<String>();
        if (!(pdSymptoms == null || pdSymptoms.isEmpty())) {
            StringTokenizer tokenizer = new StringTokenizer(pdSymptoms,",");
            while(tokenizer.hasMoreElements()) {
                symptoms.add(tokenizer.nextToken().trim());
            }
        }
        log.info("getPdSymptoms() "+symptoms.toString());
        return symptoms;
    }

    private List checkforMoreData( JSONObject jsonObject, List jsonArrayList,String categoryStr) throws JSONException, ValueFormatException,
            PathNotFoundException, UnsupportedEncodingException, RepositoryException {
        JSONObject tmpObj = new JSONObject(jsonObject.getString("jsonCategoryDetails"));
        log.info("getting checkFor more data");
        List allModels = new ArrayList();
        int count = 0;
        int offset = 0;
        int maxResults = 0;
        if (tmpObj.has("count")) {
            count = tmpObj.getInt("count");
        }
        if (tmpObj.has("offset")) {
            offset = tmpObj.getInt("offset");
        }
        if (tmpObj.has("maxResults")) {
            maxResults = tmpObj.getInt("maxResults");
        }
        offset = offset + maxResults;
        maxResults = count;
        log.info("offset "+offset);
        log.info("maxResults "+maxResults);
        log.info("count "+count);
        while (offset < count) {
            jsonObject = new JSONObject(); 
            parseAPIResponse(jsonObject, categoryStr, offset, maxResults);
            tmpObj = new JSONObject(jsonObject.getString("jsonCategoryDetails"));
            allModels.add((JSONArray)tmpObj.get("models"));
            count = tmpObj.getInt("count");
            offset = tmpObj.getInt("offset");
            maxResults = tmpObj.getInt("maxResults");
            log.info("offset inside"+offset);
            log.info("maxResults inside"+maxResults);
            log.info("count inside"+count);
            offset = offset + maxResults;
            maxResults = count;
        }
        return allModels;
    }

    private boolean isCategoryWithSymptoms(Set<String> cqSymptoms ,Set<String> pdSymptoms  ){
        boolean result = false;
        for(String symptom:pdSymptoms) {
            if ( cqSymptoms.contains(symptom)) {
                log.info("Matching Symptom "+symptom);
                result = true;
                break;
            }
        }
        return result;
    }



    // ex: //brand/cat/model
    // http://www.searspartsdirect.com/kenmore/dishwasher/model-66513213k900-repair.html
    private StringBuffer createSiteMapURLs(List jsonjsonArrayList,List<String> brandList)
            throws JSONException {
//        JSONArray jsa = new JSONArray();
        StringBuffer sitemapEntryStr = new StringBuffer();
        if (!jsonjsonArrayList.isEmpty()) {
            sitemapEntryStr
            .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sitemapEntryStr
            .append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">");

//            log.info("jsonjsonArrayList while building sitemap :"+jsonjsonArrayList.toString());
            for(Object array:jsonjsonArrayList) {
                JSONArray jsa = (JSONArray)array;
                for (int i = 0; i < jsa.length(); i++) {
                     String brand = jsa.getJSONObject(i).get("brandName").toString().trim();
                     if ( brandList.contains(brand)) {
                        sitemapEntryStr.append("<url>");
                        sitemapEntryStr.append("<loc>");
                        sitemapEntryStr.append("http://www.searspartsdirect.com/");
                        sitemapEntryStr.append(jsa.getJSONObject(i).get("brandName").toString());
                        sitemapEntryStr.append("/");
                        sitemapEntryStr.append(jsa.getJSONObject(i).get("categoryName").toString().trim());
                        sitemapEntryStr.append("/");
                        sitemapEntryStr.append("model-");
                        sitemapEntryStr.append(jsa.getJSONObject(i).get("modelNumber").toString().trim());
                        sitemapEntryStr.append("-repair.html");
                        sitemapEntryStr.append("</loc>");
                        sitemapEntryStr.append("<priority>1.0</priority>");
                        sitemapEntryStr.append("</url>");
                        sitemapEntryStr.append(System.getProperty("line.separator"));
                    } 
                        
                }
    
            }
            sitemapEntryStr.append("</urlset>");
        }
       /* if (tmpObj.has("models")) {
            sitemapEntryStr
                    .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sitemapEntryStr
                    .append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">");

            jsa = tmpObj.getJSONArray("models");
            for (int i = 0; i < jsa.length(); i++) {
                sitemapEntryStr.append("<url>");
                sitemapEntryStr.append("<loc>");
                sitemapEntryStr.append("http://www.searspartsdirect.com/");
                sitemapEntryStr.append(jsa.getJSONObject(i).get("brandName").toString());
                sitemapEntryStr.append("/");
                sitemapEntryStr.append(jsa.getJSONObject(i).get("categoryName").toString());
                sitemapEntryStr.append("/");
                sitemapEntryStr.append("model-");
                sitemapEntryStr.append(jsa.getJSONObject(i).get("modelNumber").toString());
                sitemapEntryStr.append("-repair.html");
                sitemapEntryStr.append("</loc>");
                sitemapEntryStr.append("<priority>1.0</priority>");
                sitemapEntryStr.append("</url>");
                sitemapEntryStr.append(System.getProperty("line.separator"));
            }

            sitemapEntryStr.append("</urlset>");
            // log.info("CHECK: " + sitemapEntryStr.toString());
        }*/
        return sitemapEntryStr;
    }

    private void saveSitemapURL(StringBuffer mapStr, String category)
            throws UnsupportedRepositoryOperationException,
            RepositoryException, LoginException {
        ResourceResolver resourceResolver = null;
        resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
        Session session = resourceResolver.adaptTo(Session.class);
        String destinationNodePath = "var/pdsitemap";
        Node rootNode = session.getRootNode();
        Node parentNode = rootNode.getNode(destinationNodePath);
        String localFileName = "sitemap_" + category + ".xml";
        String pathUrl = parentNode.getPath() + "/" + localFileName;

        Value data = session.getValueFactory().createValue(mapStr.toString());

        Node myNewNode = parentNode.addNode(localFileName, "nt:file");
        Node contentNode = myNewNode.addNode("jcr:content", "nt:resource");

        contentNode.setProperty("jcr:data", data);
        contentNode.setProperty("jcr:mimeType", "text/xml");

        session.save();
        log.info("*****************Completed savesitemap**************************");

    }

    // https://partsapivip.qa.ch3.s.com/pd-services/v1/modelSearch/modelListSearch?category=dryer
    private void parseAPIResponse(JSONObject jsonObject, String categoryId, int offset,int maxResults) throws JSONException, ValueFormatException,
            PathNotFoundException, RepositoryException, UnsupportedEncodingException {
        HttpClient client = new HttpClient();
        log.info("calling the api : offset "+offset);
        log.info("calling the api : maxResults "+maxResults);
        String API_URL = "http://partsapivip.qa.ch3.s.com/pd-services/v1/modelSearch/modelListSearch?category=";
        API_URL = API_URL + URLEncoder.encode(categoryId, "UTF-8");
        if (offset != 0) {
            API_URL = API_URL + "&&offset=";
            API_URL = API_URL
                    + URLEncoder.encode(Integer.toString(offset), "UTF-8");
        }
        if (maxResults != 0) {
            API_URL = API_URL + "&&limit=";
            API_URL = API_URL
                    + URLEncoder.encode(Integer.toString(maxResults), "UTF-8");
        }
        API_URL = StringUtils.replaceEach(API_URL, new String[] { "+" },
                new String[] { "%20" });
        // log.info("parseAPIResponse():step1");
        GetMethod method = new GetMethod(API_URL);

        try {
            // Provide custom retry handler is necessary
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler(2, false));
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                log.error("parseAPIResponse() failed-Status Code: "
                        + method.getStatusLine());
            } else {
                // Read the response body.
                jsonObject.put("jsonCategoryDetails",
                        method.getResponseBodyAsString());
            }

        } catch (HttpException e) {
            log.error("Error occured in parseAPIResponse() " + e.getMessage()
                    + " Exception: ");
        } catch (IOException e) {
            log.error("Error occured in parseAPIResponse() " + e.getMessage()
                    + " Exception: ");
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
       
    }
    private JSONArray retrieveJSONArray(JSONObject jsonObj) throws JSONException {
        
        JSONObject tmpObj = new JSONObject(jsonObj.getString("jsonCategoryDetails"));

//        log.info("Models in JsonArray :"+tmpObj.getJSONArray("models"));
        JSONArray array = new JSONArray();
        if (tmpObj.has("models")) {
           array = tmpObj.getJSONArray("models");
        }
        return array;
            
    }

}
