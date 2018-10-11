package com.markit.org.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class QuarterResultIdentity  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	
	@NotNull
	@Column(name = "QUARTER")
	private String quarter;

	public QuarterResultIdentity(@NotNull String employeeId, @NotNull String quarter) {
		super();
		this.employeeId = employeeId;
		this.quarter = quarter;
	}

	public QuarterResultIdentity() {
		super();
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((quarter == null) ? 0 : quarter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuarterResultIdentity other = (QuarterResultIdentity) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (quarter == null) {
			if (other.quarter != null)
				return false;
		} else if (!quarter.equals(other.quarter))
			return false;
		return true;
	}
}
