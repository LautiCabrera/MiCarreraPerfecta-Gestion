package Models;

import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Career {
	
	private final static String TABLENAME = "career";
	
	public static String getTABLENAME() {return TABLENAME;}
	
	@JsonProperty("id_career")
	private int id_career;
	public int getIdCareer() {
		return id_career;
	}
	
	public void setIdCareer(int id_career) {
		this.id_career = id_career;
	}
	
	@JsonProperty("name")
	private String name;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("title_intermediate")
	private int title_intermediate;
	public int getTitleIntermediate() {
		return title_intermediate;
	}
	
	public void setTitleIntermediate(int title_intermediate) {
		this.title_intermediate = title_intermediate;
	}
	
	@JsonProperty("description")
	private String description;
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty("duration_months")
	private int duration_months;
	public int getDurationMonths() {
		return duration_months;
	}
	
	public void setDurationMonths(int duration_months) {
		this.duration_months = duration_months;
	}
	
	@JsonProperty("id_type_career")
	private int id_type_career;
	public int getIdTypeCareer() {
		return id_type_career;
	}
	
	public void setIdTypeCareer(int id_type_career) {
		this.id_type_career = id_type_career;
	}
	
	@JsonProperty("id_modality")
	private int id_modality;
	public int getIdModality() {
		return id_modality;
	}
	
	public void setIdModality(int id_modality) {
		this.id_modality = id_modality;
	}
	
	@JsonProperty("id_branch")
	private int id_branch;
	public int getIdBranch() {
		return id_branch;
	}
	
	public void setIdBranch(int id_branch) {
		this.id_branch = id_branch;
	}
	
	@JsonProperty("id_range")
	private int id_range;
	public int getIdRange() {
		return id_range;
	}
	
	public void setIdRange(int id_range) {
		this.id_range = id_range;
	}
	
	@JsonProperty("id_user_create")
	private int id_user_create;
	public int getIdUserCreate() {
		return id_user_create;
	}
	
	public void setIdUserCreate(int id_user_create) {
		this.id_user_create = id_user_create;
	}
	
	@JsonProperty("id_user_update")
	private int id_user_update;
	public int getIdUserUpdate() {
		return id_user_update;
	}
	
	public void setIdUserUpdate(int id_user_update) {
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
	
        public static List<String[]> getCareer() {
        List<String[]> careerList = JsonDataFetcher.selectQuery("*", "career", "");
        for (String[] strings : careerList) {
            System.out.println(strings);
        }
        return careerList;
        }

        public static void createCareer(String name, int title_intermediate, String description, int duration_months, int id_type_career, int id_modality, int id_branch, int id_range) {
        String query = "INSERT INTO career (name, title_intermediate, description, duration_months, id_type_career, id_modality, id_branch, id_range, id_user_create, id_user_update, f_create, f_update) VALUES ('"
                + name + "', " + title_intermediate + ", '" + description + "', " + duration_months + ", " + id_type_career + ", " + id_modality + ", " + id_branch + ", " + id_range + ", 6, 6, NOW(), NOW())";
        ResultSetIES9021 SendQuery = DDBBConnection.SendQuery(query);
        }

        public static void modifyCareer(int id_career, String name, int title_intermediate, String description, int duration_months, int id_type_career, int id_modality, int id_branch, int id_range, int idCareer){
            String updateQuery = "UPDATE career " +
                        "SET `name` = '" + name + "', " +
                        "title_intermediate = " + title_intermediate + ", " +
                        "`description` = '" + description + "', " +
                        "`duration_months` = '" + duration_months + "', " +
                        "`id_type_career` = '" + id_type_career + "', " +
                        "`id_modality` = '" + id_modality + "', " +
                        "`id_branch` = '" + id_branch + "', " +
                        "`id_range` = '" + id_range + "', " +
                        "f_update = NOW()"+
                        " WHERE id_career = " + id_career;
            System.out.println(updateQuery);
            System.out.println(DDBBConnection.QueryVerification(updateQuery));
            DDBBConnection.SendQuery(updateQuery);
        }

        public static void deleteCareer(int id){
            String query = "DELETE FROM career WHERE id_career = "+ id;
            DDBBConnection.SendQuery(query);
        }
}