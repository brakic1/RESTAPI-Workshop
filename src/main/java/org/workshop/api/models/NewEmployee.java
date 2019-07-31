package org.workshop.api.models;


import org.apache.commons.lang3.RandomStringUtils;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	    public NewEmployee (String id, String name, String salary, String age, String profile_image) {
	        this.id = id;
	        this.name = name;
	        this.salary = salary;
	        this.age = age;
	        this.profile_image = profile_image;
	    }

	    public NewEmployee() {
	        this.id = RandomStringUtils.randomNumeric(10);
	        this.name = null;
	    }

	    public NewEmployee id(String id) {
	        this.id = id;
	        return this;
	    }
	    public String getId() {
	        return id;
	    }

	    public NewEmployee name (String name) {
	        this.name = name;
	        return this;
	    }
	    
	    public String getEmployeeName() {
	        return name;
	    }

	    public NewEmployee salary (String salary) {
	        this.salary = salary;
	        return this;
	    }
	   

	    public NewEmployee age (String age) {
	        this.age = age;
	        return this;
	    }

	    public NewEmployee profile_image (String profile_image) {
	        this.profile_image = profile_image;
	        return this;
	    }
	       
}

