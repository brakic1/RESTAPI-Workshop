package org.workshop.api.models;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * New Employee - used for post request for creating a new employee
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewEmployee {
	private String id = null;
	private String name = null;
	private String salary = null;
	private String age = null;
	private String profile_image = null;

	public NewEmployee(@JsonProperty("id") String id, @JsonProperty("name") String name,
			@JsonProperty("salary") String salary, @JsonProperty("age") String age,
			@JsonProperty("profile_image") String profile_image) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.profile_image = profile_image;
	}

	/**
	 * created an employee where name, salary and age will be random values
	 */
	public NewEmployee() {
		this.name = RandomStringUtils.randomAlphabetic(10);
		this.salary = RandomStringUtils.randomNumeric(4).toString();
		this.age = RandomStringUtils.randomNumeric(2).toString();
	}
	
	public NewEmployee(String name, String salary, String age) {
		this.name = name;
		this.salary = salary;
		this.age = age;
	}

	public NewEmployee id(String id) {
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
		return name;
	}

	public void setEmployeeName(String name) {
		this.name = name;
	}

	public NewEmployee name(String name) {
		this.name = name;
		return this;
	}

	public String getEmployeeSalary() {
		return salary;
	}

	public void setEmployeeSalary(String salary) {
		this.salary = salary;
	}

	public NewEmployee salary(String salary) {
		this.salary = salary;
		return this;
	}

	public String getEmployeeAge() {
		return age;
	}

	public void setEmployeeAge(String age) {
		this.age = age;
	}

	public NewEmployee age(String age) {
		this.age = age;
		return this;
	}

	public String getProfileImage() {
		return profile_image;
	}

	public void setProfileImage(String profile_image) {
		this.profile_image = profile_image;
	}

	public NewEmployee profile_image(String profile_image) {
		this.profile_image = profile_image;
		return this;
	}
}
