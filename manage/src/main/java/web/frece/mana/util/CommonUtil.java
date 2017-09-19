package web.frece.mana.util;



public class CommonUtil {
	
	public static String getCurrentTime() {

		return new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0, 19);
	}
	
	public static String getCurrentDate() {
		
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()).toString();
	}
}
