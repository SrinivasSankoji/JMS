package com.example.demo.health.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.demo.health.model.Person;

@Component
public class HealthService 
{
	Map<String, Person> persons = new HashMap<String, Person>();
	public HealthService() 
	{
		Person p = new Person();
		p.setId("1");
		p.setAge(21);
		p.setFirstName("Steve");
		p.setLastName("Smith");
		persons.put("1", p);

		p = new Person();
		p.setId("2");
		p.setAge(21);
		p.setFirstName("Steve");
		p.setLastName("Smith");
		persons.put("2", p);
	}
	
	public Person getPerson(String id) {
		if (persons.containsKey(id))
			return persons.get(id);
		else
			return null;
	}
	public Map<String, Person> getAll() {
		return persons;
	}

}
