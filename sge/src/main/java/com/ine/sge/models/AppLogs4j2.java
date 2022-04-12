package com.ine.sge.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "APP_LOGS4J2")
public class AppLogs4j2 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "LOG_ID")
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTRY_DATE", nullable = false, updatable = false)
	@CreatedDate
	private Date entry_date;

	@Column(name = "LOGGER")
	@Size(max=100)
	private String logger;

	@Column(name = "LOG_LEVEL")
	@Size(max=100)
	private String log_level;

	@Lob
	@Column(name = "MESSAGE")
	private String message;

	@Lob
	@Column(name = "EXCEPTION")
	private String exception;




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getLog_level() {
		return log_level;
	}

	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}



}
