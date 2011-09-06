import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
public class Crawler {
	private final String  LINK_REGEXP = "http://(\\w+\\.)*(\\w+)";
	private Pattern linkPattern;
	private Pattern searchPattern;
	private Matcher linkMatcher;
	private Matcher searchMatcher;
	private Set<URL> foundSiteSet;
	private String searchString;
	private int linkDepth;
	private Map<Integer, Set<URL>> linkMap;
	
	public Crawler(Set<URL> siteSet, int depth){
		linkDepth = depth;
		foundSiteSet= new HashSet<URL>();
		searchString = "GlassFish";
		linkMap = new HashMap<Integer, Set<URL>>();
		//Set<URL> linkSet = new HashSet<URL>();
				//linkSet.add(startURL);
		Set<URL> linkSet = siteSet;
		linkMap.put(0, linkSet);
		//parseHTML(0);
	}
	public boolean  setSerachString (String input)
	{
		if(input.trim().isEmpty())	{
			System.out.println("Search is impossible!");
			return false;
		} else	{
			this.searchString = input;
			return true;
		}
		
	}
	public void parseHTML(int level)
	{
		
			Set<URL> linkSet = new HashSet<URL>();
			String bufferStr = "";
			for(URL currentURL :linkMap.get(level))  
			{
				try {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(currentURL.openStream()));
						while(null != (bufferStr = bufferedReader.readLine())){
							linkPattern = Pattern.compile(LINK_REGEXP);
							linkMatcher = linkPattern.matcher(bufferStr);
							searchPattern = Pattern.compile(searchString, Pattern.CASE_INSENSITIVE);
							searchMatcher = searchPattern.matcher(bufferStr);
							while (linkMatcher.find()) {
								String linkStr = linkMatcher.group();
								URL url = new URL(linkStr);
								linkSet.add(url);
							}
							while (searchMatcher.find()) {
								//String foundSiteStr = searchMatcher.group();
								foundSiteSet.add(currentURL);
							}
						}
				}
				catch(Exception ex){
					System.out.println(String.format("Site %s in unavailable",currentURL));
					//ex.printStackTrace();
				}
			}
			level++;
	 	 	linkMap.put(level, linkSet);
	 	 	System.out.println(linkMap);
	 	 	if (level < linkDepth){
	 	 		parseHTML(level);
	 	 	}
	 	 	
	 	 
		
	}
	public void printResults()
	{
		System.out.println(String.format("Serching information: \"%s \" is available on following sites:", searchString));
		if (!foundSiteSet.isEmpty()) {
			for(URL url : foundSiteSet) {
				System.out.println(url);
			}
		}else{
				System.out.println("None");
		}
		}
		
			
		
}


