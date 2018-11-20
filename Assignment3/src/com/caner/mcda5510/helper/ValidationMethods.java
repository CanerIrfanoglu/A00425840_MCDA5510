package com.caner.mcda5510.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationMethods {
	
	public static String[] identifyCard(String card_no) {

		String[] cardno_cardtype = new String[2];

		if (card_no.length() == 15 && card_no.substring(0, 2).equals("34") || card_no.substring(0, 2).equals("37")) {
			cardno_cardtype[0] = card_no;
			cardno_cardtype[1] = "American Express";
			return cardno_cardtype;
		} else if (card_no.length() == 16 && card_no.substring(0, 1).equals("4")) {
			cardno_cardtype[0] = card_no;
			cardno_cardtype[1] = "Visa";
			return cardno_cardtype;
		} else if (card_no.length() == 16 && card_no.substring(0, 2).equals("51")
				|| card_no.substring(0, 2).equals("52") || card_no.substring(0, 2).equals("53")
				|| card_no.substring(0, 2).equals("54") || card_no.substring(0, 2).equals("55")) {
			cardno_cardtype[0] = card_no;
			cardno_cardtype[1] = "MasterCard";
			return cardno_cardtype;
		} else {
			cardno_cardtype[0] = card_no;
			cardno_cardtype[1] = "Unknown";
			return cardno_cardtype;

		}

	}
	
	public static boolean checkSpecialChar(String captured_string) {

		Pattern pattern = Pattern.compile(".*[\\Q;:!@#$%^*+?<>\\E].*");
		Matcher matcher = pattern.matcher(captured_string);
		boolean matches = matcher.matches();

		return matches; // Returns true if matches 
		
	}

}
