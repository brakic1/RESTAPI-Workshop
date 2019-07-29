package org.workshop.api.models;

import java.util.Objects;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Employee
 */
public class Employee {
	  	private String id = null;
	    private String employee_name = null;
	    private Integer employee_salary = null;
	    private Integer employee_age = null;
	    private String profile_image = null;

	    public Employee (String id, String employee_name, Integer employee_salary, Integer employee_age, String profile_image) {
	        this.id = id;
	        this.employee_name = employee_name;
	        this.employee_salary = employee_salary;
	        this.employee_age = employee_age;
	        this.profile_image = profile_image;
	    }

	    public Employee() {
	        id = RandomStringUtils.randomNumeric(10);
	        employee_name = "EmployeeName" + RandomStringUtils.randomAlphabetic(8);
	    }

	    public Employee id(String id) {
	        this.id = id;
	        return this;
	    }

	    /**
	     * Get id
	     *
	     * @return id
	     **/

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    /**
	     * Get Employee name
	     *
	     * @return employee name
	     **/

	    public String getEmployeeName() {
	        return employee_name;
	    }

	    public void setEmployeeName(String employee_name) {
	        this.employee_name = employee_name;
	    }

	    public Employee employeeName (String name) {
	        this.employee_name = name;
	        return this;
	    }

	    /**
	     * Get employee salary
	     *
	     * @return employee_salary
	     **/

	    public Integer getEmployeeSalary() {
	        return employee_salary;
	    }

	    public void setEmployeeSalary(Integer employee_salary) {
	        this.employee_salary = employee_salary;
	    }

	    public Employee employeeSalary (Integer salary) {
	        this.employee_salary = salary;
	        return this;
	    }
	    
	    /**
	     * Get employee age
	     *
	     * @return employee_age
	     **/

	    public Integer getEmployeeAge() {
	        return employee_age;
	    }

	    public void setEmployeeAge(Integer employee_age) {
	        this.employee_age = employee_age;
	    }

	    public Employee employeeAge (Integer age) {
	        this.employee_age = age;
	        return this;
	    }

	    /**
	     * Get profile image
	     *
	     * @return profile_image
	     **/

	    public String getProfileImage() {
	        return profile_image;
	    }

	    public void setProfileImage(String profile_image) {
	        this.profile_image = profile_image;
	    }

	    public Employee profileImage (String image) {
	        this.profile_image = image;
	        return this;
	    }


	    @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }
	        Employee employee = (Employee) o;
	        return 	Objects.equals(this.id, employee.id) &&
	                Objects.equals(this.employee_name, employee.employee_name) &&
	                Objects.equals(this.employee_salary, employee.employee_salary) &&
	                Objects.equals(this.employee_age, employee.employee_age) &&
	                Objects.equals(this.profile_image, employee.profile_image);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, employee_name, employee_salary, employee_age, profile_image);
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("class Employee {\n");

	        sb.append("    id: ").append(toIndentedString(id)).append("\n");
	        sb.append("    category: ").append(toIndentedString(employee_name)).append("\n");
	        sb.append("    name: ").append(toIndentedString(employee_salary)).append("\n");
	        sb.append("    photoUrls: ").append(toIndentedString(employee_age)).append("\n");
	        sb.append("    tags: ").append(toIndentedString(profile_image)).append("\n");
	        sb.append("}");
	        return sb.toString();
	    }

	    /**
	     * Convert the given object to string with each line indented by 4 spaces
	     * (except the first line).
	     */
	    private String toIndentedString(Object o) {
	        if (o == null) {
	            return "null";
	        }
	        return o.toString().replace("\n", "\n    ");
	    }

}

