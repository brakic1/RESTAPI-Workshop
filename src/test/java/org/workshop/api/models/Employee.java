package org.workshop.api.models;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Employee - an object used to list already created employees
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
	private String id = null;
	private String employee_name = null;
	private String employee_salary = null;
	private String employee_age = null;
	private String profile_image = null;

	public Employee(String id, String name, String salary, String age, String profile_image) {
		this.id = id;
		this.employee_name = name;
		this.employee_salary = salary;
		this.employee_age = age;
		this.profile_image = profile_image;
	}

	/**
	 * created an employee where employee_name, employee_salary and employee_age
	 * will be random values
	 */
	public Employee() {
		this.employee_name = RandomStringUtils.randomAlphabetic(10);
		this.employee_salary = RandomStringUtils.randomNumeric(4).toString();
		this.employee_age = RandomStringUtils.randomNumeric(2).toString();
	}

	public Employee id(String id) {
		this.id = id;
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employee_name;
	}

	public void setEmployeeName(String name) {
		this.employee_name = name;
	}

	public Employee name(String name) {
		this.employee_name = name;
		return this;
	}

	public String getEmployeeSalary() {
		return employee_salary;
	}

	public void setEmployeeSalary(String salary) {
		this.employee_salary = salary;
	}

	public Employee salary(String salary) {
		this.employee_salary = salary;
		return this;
	}

	public String getEmployeeAge() {
		return employee_age;
	}

	public void setEmployeeAge(String age) {
		this.employee_age = age;
	}

	public Employee age(String age) {
		this.employee_age = age;
		return this;
	}

	public String getProfileImage() {
		return profile_image;
	}

	public void setProfileImage(String profile_image) {
		this.profile_image = profile_image;
	}

	public Employee profile_image(String profile_image) {
		this.profile_image = profile_image;
		return this;
	}

}
