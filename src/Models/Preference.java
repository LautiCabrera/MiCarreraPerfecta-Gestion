package Models;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;
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
	
        public static List<String[]> getPreferences() {
            List<String[]> preferenceList = JsonDataFetcher.selectQuery("*", "preference", "");
            for (String[] strings : preferenceList) {
                System.out.println(strings);
            }
            return preferenceList;
        }

        public static void createPreference(String group, String name) {
            String query = "INSERT INTO preference (`group`, `name`, id_user_create, id_user_update, f_create, f_update) VALUES ('"
                    + group + "', '" + name + "', 6, 6, NOW(), NOW())";
            ResultSetIES9021 SendQuery = DDBBConnection.SendQuery(query);
        }

        public static void modifyPreference(String group, String name, int id){
            String updateQuery = "UPDATE preference " +
                        "SET `group` = '" + group + "', " +
                        "`name` = '" + name + "', " +
                        "f_update = NOW()"+
                        " WHERE id_preference = " + id;
            System.out.println(updateQuery);
            System.out.println(DDBBConnection.QueryVerification(updateQuery));
            DDBBConnection.SendQuery(updateQuery);
        }

        public static void deletePreference(int id){
            String query = "DELETE FROM preference WHERE id_preference = "+ id;
            DDBBConnection.SendQuery(query);
        }
}