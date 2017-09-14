package test.frece.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class GenerateTestData {
	private static Random random = new Random(47);
	
	public static String generateNameStr(String language, String gender) {
		String res = "";
		res = generateRandomString("EN", 6, 15);
		return res;		
	}
	
	public static String generateRandomString(String lang, Integer minLength,  Integer maxLength) {
		minLength = minLength == null? 0:minLength;
		maxLength = maxLength == null? 0:maxLength;
		if(maxLength == 0 || minLength > maxLength) {
			return "";
		}
		
		String res = "";
		if("CN".equals(lang)) {
			
		}else {
			res = getEnStr(minLength);
			if(minLength != maxLength) {
				res += getEnStr(random.nextInt(maxLength - minLength)) ;
			}			
		}
		
		return res;		
	}
	
	public static String getEnStr(int length) {
		if(length == 0) {
			return "";
		}
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String res = "";
		
		for (int i = 0; i < length; i++) {
			res += str.charAt(random.nextInt(52));
		}		
		
		return res;		
	}

	public static String generateGender() {
		return random.nextBoolean() ? "Male":"Female";
	}
	
	public static Date generateDate(String begin, String end) throws Exception {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		long beginTime = sdf.parse(begin).getTime();
		long endTime = sdf.parse(end).getTime();
		
		if(beginTime > endTime) {
			date = sdf.parse(begin);
		}else {
			long randomDateTme = beginTime + (long) (random.nextDouble() * (endTime - beginTime));  
			date = new Date(randomDateTme);
		}		
		
		return date;		
	}
	
	public static String generateNumber(int length) {
		if(length <= 0) {
			return "0";
		}
		String res = "";
		String str = "0123456789";
		for (int i = 0; i < length; i++) {
			res += str.charAt(random.nextInt(10));
		}
		return res;		
	}
	public static String generateNumber(long fromNum, long toNum, Boolean isIntNum) {
		if(fromNum > toNum) {
			return "" + fromNum;
		}		
		String res = "";
		if(isIntNum) {
			long num = fromNum + (long)(random.nextDouble() * (toNum - fromNum));
			res += num;
		}else {
			double num = fromNum + random.nextDouble() * (toNum - fromNum);
			res += num;
		}
		
		return res;		
	}
	
	

	public static List<Document> generateListDocForCustomTable(String language, int quantity) throws Exception{
		List<Document> resList = new ArrayList<Document>();
		
		
		for (int i = 0; i < quantity; i++) {
			Document document = new Document();
			document.append("Name", getEnStr(5 + random.nextInt(15)));
			document.append("Gender", generateGender());
			document.append("Birth", generateDate("1970-01-01", "2000-12-31"));
			document.append("Phone Number", generateNumber(11));
			document.append("Email", getEnStr(10));
			document.append("Address", getEnStr(20 + random.nextInt(40)));
			document.append("Join Date", generateDate("2008-01-01", "2015-12-31"));
//			document.put("_id", document.getString("Name") + document.getString("Phone Number"));
			resList.add(document);
		}
		
		
		
		return resList;		
	}
	
	public static void main(String[] args) throws Exception {
		 List<Document> list = generateListDocForCustomTable("EN", 100);
		for (Document document : list) {
			System.out.println(document.toJson());
		}
		 
	}
	
	

}
