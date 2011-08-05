import java.io.File;
import java.io.FilenameFilter;
import java.io.FileFilter;
import java.util.*;
public class FileSearch {
	//Initialization collection with found files 
	//class to filter required files
	private FilenameFilter searchFilter;
	private FileFilter isFolder;
	//Initialization collection with list of searching files
	private TreeSet foundFiles = new TreeSet();
	public TreeSet searchingFiles = new TreeSet();
	private final static TreeSet IGNORE_FOLDERS = new TreeSet();
    static {
    	IGNORE_FOLDERS.add("System Volume Information");
    	IGNORE_FOLDERS.add(".");
    	IGNORE_FOLDERS.add("..");
	}

	public void findFiles (File search_folder)	{
		searchFilter=new FileFilterer(searchingFiles);
		isFolder=new FolderFilter(IGNORE_FOLDERS);
		
		//Oleh: These checks should be done before instance of this class is created
		//if (search_folder.exists())
			//if (search_folder.isDirectory()){
		//Igor: Done.
				File[] fileList = search_folder.listFiles(searchFilter);
				File[] folderList = search_folder.listFiles(isFolder);
				
				for(int file_index = 0;file_index<fileList.length;file_index++)	{
								
					foundFiles.add(fileList[file_index].getPath());
				}
				for(int file_index = 0;file_index<folderList.length;file_index++)	{
					//Oleh: Move all these checks to special DirectoryFilter implements FileFilter. And move "special" string to constants.
					//Igor. Done.
				//	if ((folder_list[file_index].isDirectory())&& (!folder_list[file_index].getName().equals("System Volume Information")) && (!folder_list[file_index].getName().equals(".."))&& (!folder_list[file_index].getName().equals(".")))
					//{
						//Oleh: Why do you need this system.out?
						/*Igor: In origin code I did not this output. But during searching in huge folder
						for example in C:\ it is not clear "Is the program work correct or some stuck happened.
						Therefore I set this redundant out. Fixed.	*/
						//System.out.println(folder_list[file_index].getPath());
						findFiles(folderList[file_index]);
					//}
						
				}
			//}
			//else 
			//	System.out.println("Spesified path is not folder");
		//else
		//	System.out.println("Root folder does not exist");
	}
		public void printResults ()	{
		//Iterator ind=foundFiles.iterator();
		//Oleh: starting from Java 5 it can be changed to for (String fileName in foundFiles) {...} This si possible because foundFiles implements Iterable interface.
		//Igor: Cool, it works. Did I use correct expression?
			//while (ind.hasNext())
		for (Object fileName : foundFiles)	{
			System.out.println(fileName.toString());
			//System.out.println(ind.next());
		}
	  }
}
		/* Implementation of filter class. This class is used to filter required files
		 * in current folder*/
class FileFilterer implements FilenameFilter{
	private TreeSet fileNames;
	public FileFilterer(TreeSet fileNames){
		this.fileNames = fileNames;
		}
	public boolean accept(File directory, String filename) {
			 //Oleh: This can be changed to return name.contains(filename);
			 //Igor. Fixed  
		/*  if (name.contains(filename))
		{
			return true;
		}
		else 
		{
			return false;
		}*/
			 return fileNames.contains(filename);
			 }
		}
class FolderFilter implements FileFilter{
	private TreeSet fileNames;
	public FolderFilter(TreeSet fileNames){
		this.fileNames = fileNames;
		}
	
	public boolean accept(File fileName) {
		if (fileName.isDirectory())	{
			return !fileNames.contains(fileName.getName());
		}
		else 
			return false;
				
	}
}


