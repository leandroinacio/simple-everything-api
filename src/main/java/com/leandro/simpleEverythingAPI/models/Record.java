package com.leandro.simpleEverythingAPI.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "record")
public class Record implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Indexed(direction = IndexDirection.ASCENDING)
	private String id;
	
	@NotNull
	private String userId;
	
	@NotNull
	private String recordTitle;
	
	@NotNull
	private String recordDescription;

	@NotNull
	private List<String> tag;

	private Date dtCreated;
	private Date dtUpdated;

	public Record() {
		super();
	}

	public Record(String id, String userId, String recordTitle, String recordDescription, List<String> tag,
			Date dtCreated, Date dtUpdated) {
		super();
		this.id = id;
		this.userId = userId;
		this.recordTitle = recordTitle;
		this.recordDescription = recordDescription;
		this.tag = tag;
		this.dtCreated = dtCreated;
		this.dtUpdated = dtUpdated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRecordTitle() {
		return recordTitle;
	}

	public void setRecordTitle(String recordTitle) {
		this.recordTitle = recordTitle;
	}

	public String getRecordDescription() {
		return recordDescription;
	}

	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}

	public List<String> getTag() {
		return tag;
	}

	public void setTag(List<String> tag) {
		this.tag = tag;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public Date getDtUpdated() {
		return dtUpdated;
	}

	public void setDtUpdated(Date dtUpdated) {
		this.dtUpdated = dtUpdated;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", userId=" + userId + ", recordTitle=" + recordTitle + ", recordDescription="
				+ recordDescription + ", tag=" + tag + ", dtCreated=" + dtCreated + ", dtUpdated=" + dtUpdated + "]";
	}
}