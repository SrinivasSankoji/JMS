package com.example.demo.health.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Person implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	String id;
	String firstName;
	String lastName;
	int    age;

}
