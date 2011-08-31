import java.io.File;
import java.io.FilenameFilter;
import java.io.FileFilter;
import java.util.*;
/*
 List of changes after third review:
 1.Public members changed to Private. Added Public function to work with private members.
 2. Iteration by HASH MAP changed to For in against using iterator
 3. Collection instances are using now generics
 4. Manual string reconstruction changed to String.format 
 5. Variables now are declared with more basis classes(Set against TreeSet,... )
   
 */
public class FileFinder {
	private final static TreeSet<String> IGNORED_FOLDERS = new TreeSet<String>();
    static {
    	IGNORED_FOLDERS.add("System Volume Information");
    	IGNORED_FOLDERS.add(".");
    	IGNORED_FOLDERS.add("..");
	}
	private FilenameFilter filenameFilter;
	private FileFilter folderFilter;
	private Map<File, Set<File>> foundFiles = new HashMap<File, Set<File>>();
	private Set<String> searchingFiles = new TreeSet<String>();
	private Set<File> foundFileSet;
	public void setSearchingFiles(String fileName)
	{
		
		if (!fileName.trim().isEmpty()) {
				searchingFiles.add(fileName);
		} else {
			System.out.println("File Name can't be None!!!");
		}
		
	}
	public void findFiles (File rootFolder)	{
		filenameFilter = new FileFilterer(searchingFiles);
		folderFilter = new FolderFilter(IGNORED_FOLDERS);
		File[] fileList = rootFolder.listFiles(filenameFilter);
		foundFileSet = new TreeSet<File>();
		foundFileSet.addAll(Arrays.asList(fileList));
		if (!foundFileSet.isEmpty()) {
			foundFiles.put(rootFolder, foundFileSet);
		}
		File[] folderList = rootFolder.listFiles(folderFilter);
		for (File directory : folderList) {
			findFiles(directory);
		}
	}
		public void printResults ()	{
		Set<File> filesSet = new TreeSet<File>();
		Set<File> fileKeys = foundFiles.keySet();
		for (File baseFolder : fileKeys)
		{
			System.out.println(String.format("In folder \"%s\" found file(s):", baseFolder));
			filesSet=foundFiles.get(baseFolder);
			for(Object fileObj :filesSet){
				File file = (File)fileObj;
				System.out.println(file.getName());
		}
		}
	  }
 private class FileFilterer implements FilenameFilter{
	private Set<String> fileNames;
	public FileFilterer(Set<String> fileNames){
		this.fileNames = fileNames;
		}
	public boolean accept(File directory, String filename) {
			 return fileNames.contains(filename);
			 }
		}
private class FolderFilter implements FileFilter{
	private Set<String> fileNames;
	public FolderFilter(Set<String> fileNames){
				this.fileNames = fileNames;
		}
	
	public boolean accept(File fileName) {
		if (fileName.isDirectory())	{
			return !fileNames.contains(fileName.getName());
		}else { 
				return false;
			   }
		//Encountered with problem to return in one string
		//return !fileNames.contains(fileName.getName())||fileName.isDirectory();
				
	}
}		
}
