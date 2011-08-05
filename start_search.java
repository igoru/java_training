import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Please read these official Java naming conventions: http://www.oracle.com/technetwork/java/codeconventions-135099.html#367
public class start_search {

	public static void main (String args[])
	//Opening curly bracket should be on the same line as header statement.
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		//Don't declare several variables in one line. Use separate line for each.
		String root_folder="", file_name="", is_additional_file="y";
		try
		{
			System.out.println("Set root folder:");
			//Add spaces around "="
			root_folder=input.readLine();
			
			//Why are there two spaces after File?
			File  root_Directory=new File(root_folder);
			
			//Check if root folder exists at this point and if is doesn't -- print corresponding error message.
			file_search run_search = new file_search();
			System.out.println("Input file name to search:");
			file_name=input.readLine();
			run_search.files_to_search.add(file_name);
			while (!is_additional_file.equals("n"))
			{
				System.out.println("Do you want input aonother file(s) to search(y/n)?[y]");
				is_additional_file=input.readLine();
				
				if((is_additional_file.isEmpty()) || (is_additional_file.equals("y")))
				{
					System.out.println("Input file name to search:");
					file_name=input.readLine();
					run_search.files_to_search.add(file_name);
					is_additional_file="y";
				}
				else
					if (!is_additional_file.equals("n"))
						System.out.println("Wrong choice!!!");
				
				
			}
			
			run_search.find(root_Directory);
			System.out.println("---------------FOUND FILES---------------");
			run_search.print_results();
			
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			
		}
		
		//File  root_Directory=new File("C:\\test");
		
		
		
		
		}
}
