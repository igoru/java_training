import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
public class CrawlerConsole {
	 public static void main(String[] args)  {
		 Set<URL> siteSet = new HashSet<URL>();
		 String addSite = "";
		 BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		 try {
			 	while (!addSite.equals("n"))	{
			 		System.out.println("Add site(s) for searching (n-no more sites )");
			 		addSite=input.readLine();
			 		if (!addSite.equals("n") && !addSite.trim().isEmpty()){
			 			URL url = new URL(addSite);
			 			siteSet.add(url);
			 		}
			 	}
			 	System.out.println("Input seaching depth:");
			 	int searchDepth=Integer.parseInt(input.readLine());
			 	Crawler  crawler= new Crawler(siteSet, searchDepth);
			 	System.out.println("Input searching string:");
			 	if (crawler. setSerachString(input.readLine())){
			 		crawler.parseHTML(0);
			 		crawler.printResults();
			 	}
		 }
		 catch(Exception ex){
			 ex.printStackTrace();
		 }
	 }
	

}
