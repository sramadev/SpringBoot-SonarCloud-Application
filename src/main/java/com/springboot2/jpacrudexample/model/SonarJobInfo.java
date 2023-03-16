package com.springboot2.jpacrudexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sonarjobinfo")
public class SonarJobInfo {

	private long id;
	private String jobName;
	private String jobType;
	private String emailId;
	
	public SonarJobInfo() {
		
	}
	
	
	
	public SonarJobInfo(long id, String jobName, String jobType, String emailId) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.jobType = jobType;
		this.emailId = emailId;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	@Column(name = "job_name", nullable = false)
	public String getJobName() {
		return jobName;
	}



	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	@Column(name = "job_type", nullable = false)
	public String getJobType() {
		return jobType;
	}



	public void setJobType(String jobType) {
		this.jobType = jobType;
	}


	@Column(name = "email_address", nullable = false)
	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	@Override
	public String toString() {
		return "SonarJobInfo [id=" + id + ", jobName=" + jobName + ", jobType=" + jobType + ", emailId=" + emailId
				+ "]";
	}
	
		
}
