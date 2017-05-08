import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Unzip {
	public static void main(String[] args) {

        Path path =Paths.get("/Users/mislam/Desktop/Speeds/08");
        GetFilesInTheDirectory gf =new GetFilesInTheDirectory();
        List<String> allfilles= gf.getAllIndividuals(path);
        for (String string : allfilles) {
			//System.out.println(string);
			String dest = string.substring(0,string.lastIndexOf("/"));
			//System.out.println(dest);
			UnzipUtility unzipper = new UnzipUtility();
	        try {
	            unzipper.unzip(string, dest);
	            File f =new File(string);
	           // f.delete();
	        } catch (Exception ex) {
	            // some errors occurred
	            ex.printStackTrace();
	        }
		}
        
	}
}
class GetFilesInTheDirectory extends SimpleFileVisitor<java.nio.file.Path> {
@Override
   public FileVisitResult postVisitDirectory(java.nio.file.Path dir,
                                         IOException exc) {
       System.out.format("Directory: %s%n", dir);
       return FileVisitResult.CONTINUE;
   }


public List<String> getAllIndividuals(java.nio.file.Path path) {
    final List<String> list = new ArrayList<>();

    try {
        Files.walkFileTree(path, new SimpleFileVisitor<java.nio.file.Path>()
        {
            @Override
            public FileVisitResult visitFile(java.nio.file.Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isDirectory()) {
                    return FileVisitResult.CONTINUE;
                }
                String id = file.toFile().toString();
                if(id.endsWith("zip"))
                {
               	list.add(id); 
                }
                
                return FileVisitResult.CONTINUE;
            }
        });
    } catch (IOException e) {
        System.out.println("Error getting all individuals");
    }

    return list;
}

}