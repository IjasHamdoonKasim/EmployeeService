package com.sgic.employee.server.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "employee-service", name = "employee")
public class Employee implements Serializable {

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="designationId")
	private Designation getDesignation()
	{
		return getDesignation();
	}
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;

	private String firstName;
	private String lastName;
	private String gender;
	private String emailId;
	//private Long designationId;
	private Integer phoneNo;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

//	public Long getDesignationId() {
//		return designationId;
//	}
//
//	public void setDesignationId(Long designationId) {
//		this.designationId = designationId;
//	}

	public Integer getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
