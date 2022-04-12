package com.ine.common.dto.UniverseDefaultSchema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
abstract class AuditModel<U> implements Serializable {

	@JsonProperty("createdBy")
	private U createdBy;

	@JsonProperty("lastModifiedBy")
	private U lastModifiedBy;

	@JsonProperty("createdAt")
    private Date createdAt;

	@JsonProperty("updatedAt")
    private Date updatedAt;


	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getCreatedAt() {
        return createdAt;
    }

	public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public Date getUpdatedAt() {
        return updatedAt;
    }

	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
	//endregion
}
