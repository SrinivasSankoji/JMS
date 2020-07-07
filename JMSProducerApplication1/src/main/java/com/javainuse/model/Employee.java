package com.javainuse.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Employee implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String empName;
	private String empId;

}
