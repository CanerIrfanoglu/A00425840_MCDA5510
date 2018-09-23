import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import java.io.BufferedReader;

public class SimpleCsvParser {
	
	 static int validRow = 0;
	 static int invalidRow = 0;

	 public static void main(String[] args) {
		
		 final long startTime = System.currentTimeMillis();

		 SimpleLogging.main(null);
		 InvalidLogging.main(null);
		 ParseandWrite(); 
		 final long endTime = System.currentTimeMillis();
		 //System.out.println("Total execution time: " + (endTime - startTime) +" ms");
		 //System.out.println("Number of Valid Rows: " + validRow + " Number of Invalid Rows: " + invalidRow);
		 SimpleLogging.LOGGER.info("Total execution time: " + (endTime - startTime) +" ms");
		 SimpleLogging.LOGGER.info("Number of Valid Rows: " + validRow + " Number of Invalid Rows: " + invalidRow);
		 //InvalidnLogging.LOGGER.info("HELLO FROM InvalidExeption");
	 }
		
	 public static void ParseandWrite (){

		FileWriter fileWriter;
		
		try {
			fileWriter = new FileWriter("./Output/CustomerData.csv");
			BufferedWriter writer = new BufferedWriter(fileWriter);
			CSVPrinter cvPrinter = new CSVPrinter(writer,CSVFormat.EXCEL);
			DirWalker.main(null);
			//System.out.println(DirWalker.csvPaths.size());
			
			for (int i=0; i<DirWalker.csvPaths.size(); i++) {
	
				final String filePath = DirWalker.csvPaths.get(i);
				//System.out.println(filePath);

				BufferedReader b_in = new BufferedReader(new FileReader(filePath)); 
						
				if(i == 0) { //Only write header to csv file in first iteration
					Iterable<CSVRecord> csvFileHeader = CSVFormat.EXCEL.parse(b_in);
					for (CSVRecord header_row : csvFileHeader) {
						cvPrinter.printRecord(header_row);
						break;
					}
				}
			    Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(b_in); 
			    
				for (CSVRecord record : records) {
					
					if (record.size() != 10     ||// First check the record size to prevent out of bounds
						record.get(0).isEmpty() || 
						record.get(1).isEmpty() || 
						record.get(2).isEmpty() || 
						record.get(3).isEmpty() || 
						record.get(4).isEmpty() || 
						record.get(5).isEmpty() || 
						record.get(6).isEmpty() || 
						record.get(7).isEmpty() || 
						record.get(8).isEmpty() || 
						record.get(9).isEmpty()  ) {						
						
						String bad_record = "";
						for (int j=0; j<record.size(); j++) {
							bad_record = bad_record + record.get(j);
						}
						InvalidLogging.LOGGER.finest(bad_record + " ");
						invalidRow++;
						
					}
				
					else {
						cvPrinter.printRecord(record);
						validRow++;
					}
					//System.out.println(record);
				}
	
			}
			
			cvPrinter.close();
			writer.close();
			System.out.println("Compilation was successful!" );
		}catch (IOException e1) {
			SimpleLogging.LOGGER.warning("Error occured on read/writing: " + e1.toString());
			
		}catch(ArrayIndexOutOfBoundsException e2) {
		    SimpleLogging.LOGGER.warning("Out of Bounds Error: " + e2.toString());
		}
		
	}

}
