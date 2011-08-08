import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Please read these official Java naming conventions: http://www.oracle.com/technetwork/java/codeconventions-135099.html#367
//Igor: Fixed
public class StartSearch {

	public static void main (String args[]){
		//Opening curly bracket should be on the same line as header statement.
		//Igor Fixed
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			
			//Don't declare several variables in one line. Use separate line for each.
			//Igor. Fixed
			String rootFolder = "";
			String fileName = "";
			String addFile = "y";
			try {
				System.out.println("Set root folder:");
				//Add spaces around "="
				//Igor. Fixed. Is it also relevant for cases when "=" is put of big expression?
				//For example: for(int file_index = 0;file_index<file_list.length;file_index++)
				rootFolder = input.readLine();
				
				//Why are there two spaces after File?
				File rootDirectory = new File(rootFolder);
				//Igor: Actually I do not know. I thought that it is not important. Understood, I will pay attention on count of spaces
				//Fixed
				
				//Check if root folder exists at this point and if is doesn't -- print corresponding error message.
				//Igor: Done
				if (rootDirectory.exists()){
					if (rootDirectory.isDirectory()){
//Oleh: Usually it is better to name variables, fields and classes as noun. In this case it would be "fileFinder"
						FileSearch runSearch = new FileSearch();
						System.out.println("Input file name to search:");
//Oleh: spaces around " = "
						fileName=input.readLine();
						runSearch.searchingFiles.add(fileName);
						while (!addFile.equals("n"))	{
//Oleh: another (typo)
							System.out.println("Do you want input aonother file(s) to search(y/n)?[y]");
							addFile = input.readLine();
					
							if((addFile.isEmpty()) || (addFile.equals("y"))){
								System.out.println("Input file name to search:");
								fileName=input.readLine();
								runSearch.searchingFiles.add(fileName);
								addFile = "y";
							}
							else
//Oleh: Always use curly brackets even if there is only one operator after it. This is a holy rule.
								if (!addFile.equals("n"))
//Oleh: Why wrong?
									System.out.println("Wrong choice!!!");
									
				}
				
						runSearch.findFiles(rootDirectory);
						System.out.println("---------------FOUND FILES---------------");
						runSearch.printResults();
					}
//Oleh: "else" should go on the same line as closing bracket, i.e. "} else {" or "} else if (...) {"
					else
						System.out.println("Spesified path is not folder");
				}
				else 
					System.out.println("Root folder does not exist");
				
				
				
			}
			catch (IOException e)
//Oleh: bracket should be on the same line
			{
				System.out.println(e.getMessage());
				
			}
				
		}

}
