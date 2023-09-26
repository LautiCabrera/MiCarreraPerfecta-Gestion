package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.sql.Date;

public class Branch {

	private final static String TABLENAME = "branch";

	public static String getTABLENAME() {
		return TABLENAME;
	}

	private int id_branch;

	public int getIdBranch() {
		return id_branch;
	}

	public void setIdBranch(int id_branch) {
		this.id_branch = id_branch;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String id_user_create;

	public String getIdUserCreate() {
		return id_user_create;
	}

	public void setIdUserCreate(String id_user_create) {
		this.id_user_create = id_user_create;
	}

	private String id_user_update;

	public String getIdUserUpdate() {
		return id_user_update;
	}

	public void setIdUserUpdate(String id_user_update) {
		this.id_user_update = id_user_update;
	}

	private String f_create;

	public String getFCreate() {
		return f_create;
	}

	public void setFCreate(String f_create) {
		this.f_create = f_create;
	}

	private String f_update;

	public String getFUpdate() {
		return f_update;
	}

	public void setFUpdate(String f_update) {
		this.f_update = f_update;
	}

}