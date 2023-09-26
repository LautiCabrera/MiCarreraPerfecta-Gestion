package Models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.sql.Date;
public class University {
	
	private final static String TABLENAME = "University";
	
	public static String getTABLENAME() {return TABLENAME;}
	
}