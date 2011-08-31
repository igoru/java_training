import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchConsole {
	public static void main (String args[]){
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String rootFolder = "";
		String fileName = "";
		String addFile = "y";
		try {
			System.out.println("Set root folder:");
			rootFolder = input.readLine();
			File rootDirectory = new File(rootFolder);
			if (rootDirectory.exists()){
				if (rootDirectory.isDirectory()){
					FileFinder fileFinder = new FileFinder();
					System.out.println("Input file name to find:");
					fileName = input.readLine();
					fileFinder.setSearchingFiles(fileName);
					while (!addFile.equals("n"))	{
						System.out.println("Do you want input another file(s) to search(y/n)?[y]");
						addFile = input.readLine();
						if((addFile.isEmpty()) || (addFile.equals("y"))){
							System.out.println("Input file name to search:");
							fileName = input.readLine();
							fileFinder.setSearchingFiles(fileName);
							addFile = "y";
						}else {
								if (!addFile.equals("n")){
									System.out.println("Wrong choice!!! Should be \"y\" or \"n\"");
								}
							}
					}
					fileFinder.findFiles(rootDirectory);
					System.out.println("---------------FOUND FILES---------------");
					fileFinder.printResults();
				}else{
						System.out.println("Specified path is not folder");
					}
			}else { 
					System.out.println("Root folder does not exist");
			 }
			
		}
		catch (IOException e){
				System.out.println(e.getMessage());
			
		}
			
	}

}
