import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class file_search {
//Initialization collection with found files 
private TreeSet found_files= new TreeSet();
//class to filter required files
private FilenameFilter search_files;
//Initialization collection with list of searching files
public TreeSet files_to_search = new TreeSet();
public void find (File search_folder)
{
	search_files=new FileFilter(files_to_search);
	
	//Oleh: These checks should be done before instance of this class is created
	if (search_folder.exists())
		if (search_folder.isDirectory())
		{
			File[] file_list=search_folder.listFiles(search_files);
			File[] folder_list=search_folder.listFiles();
			
			for(int file_index=0;file_index<file_list.length;file_index++)
			{
							
				found_files.add(file_list[file_index].getPath());
			}
			for(int file_index=0;file_index<folder_list.length;file_index++)
			{
				//Oleh: Move all these checks to special DirectoryFilter implements FileFilter. And move "special" string to constants.
				if ((folder_list[file_index].isDirectory())&& (!folder_list[file_index].getName().equals("System Volume Information")) && (!folder_list[file_index].getName().equals(".."))&& (!folder_list[file_index].getName().equals(".")))
				{
					//Oleh: Why do you need this system.out?
					/*Igor: In origin code I did not this output. But during searching in huge folder
					for example in C:\ it is not clear "Ip the program work correct or some stuck happened.
					Therefore I set hear this redundant out. Fixed.
					*/
					//System.out.println(folder_list[file_index].getPath());
					find(folder_list[file_index]);
				}
					
			}
		}
		else 
			System.out.println("Spesified path is not folder");
	else
		System.out.println("Root folder does not exist");
}
	public void print_results ()
{
	Iterator ind=found_files.iterator();
	//Oleh: starting from Java 5 it can be changed to for (String fileName in foundFiles) {...} This si possible because foundFiles implements Iterable interface.
	while (ind.hasNext())
	{
		System.out.println(ind.next());
	}
  }

}

/* Implementation of filter class. This class is used to filter required files
 * in current folder*/
class FileFilter implements FilenameFilter
{
	
	private TreeSet name;
  	public FileFilter(TreeSet name)
  {
	    this.name = name;
  }
	 public boolean accept(File directory, String filename) 
	 {
	 //Oleh: This can be changed to return name.contains(filename);
		  if (name.contains(filename))
		{
			return true;
			
		}
		else 
		{
			return false;
		}		 
	 }
	}
