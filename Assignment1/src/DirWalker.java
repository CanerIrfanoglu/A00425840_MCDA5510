import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirWalker {
	
	public static List<String> csvPaths = new ArrayList<>();


    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();
        

        try {
        if (list == null) {
        	//System.out.println("List is null returning...");
        	return;
        }

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                //System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else if (f.getAbsolutePath().contains(".csv")) { //Making the csv check here
                //System.out.println( "File:" + f.getAbsoluteFile() );
                csvPaths.add(f.getAbsoluteFile().toString());
            }
        }
        } catch (Exception e ) {
        	SimpleLogging.LOGGER.warning("Exception occured while walking directories. " + e.toString());
        }
    }
    
    public static void main(String[] args) {
    	DirWalker fw = new DirWalker();
        fw.walk("/Users/Caner/Desktop/SMU_Class/Software_Development_5510/A00425840_MCDA5510/Assignment1/Sample Data");
    }

}