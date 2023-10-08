package Models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.sql.Date;
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
	
}