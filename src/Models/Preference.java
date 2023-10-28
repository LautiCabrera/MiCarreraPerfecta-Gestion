package Models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.sql.Date;
public class Preference {
	
	private final static String TABLENAME = "preference";
	
	public static String getTABLENAME() {return TABLENAME;}
	
	@JsonProperty("id_preference")
	private int id_preference;
	public int getIdPreference() {
		return id_preference;
	}
	
	public void setIdPreference(int id_preference) {
		this.id_preference = id_preference;
	}
	
	@JsonProperty("group")
	private String group;
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	@JsonProperty("name")
	private String name;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("id_word_key")
	private int id_word_key;
	public int getIdWordKey() {
		return id_word_key;
	}
	
	public void setIdWordKey(int id_word_key) {
		this.id_word_key = id_word_key;
	}
	
	@JsonProperty("id_user_create")
	private String id_user_create;
	public String getIdUserCreate() {
		return id_user_create;
	}
	
	public void setIdUserCreate(String id_user_create) {
		this.id_user_create = id_user_create;
	}
	
	@JsonProperty("id_user_update")
	private String id_user_update;
	public String getIdUserUpdate() {
		return id_user_update;
	}
	
	public void setIdUserUpdate(String id_user_update) {
		this.id_user_update = id_user_update;
	}
	
	@JsonProperty("f_create")
	private String f_create;
	public String getFCreate() {
		return f_create;
	}
	
	public void setFCreate(String f_create) {
		this.f_create = f_create;
	}
	
	@JsonProperty("f_update")
	private String f_update;
	public String getFUpdate() {
		return f_update;
	}
	
	public void setFUpdate(String f_update) {
		this.f_update = f_update;
	}
	
}