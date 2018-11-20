package com.caner.mcda5510.helper;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class GetCurrentDT {
	
	public static String getTime() {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now();  
	return dtf.format(now); 
	}
}
