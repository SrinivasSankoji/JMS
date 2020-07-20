package com.example.demo.health.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.health.model.Person;
import com.example.demo.health.service.HealthService;

@RestController
@RequestMapping("/persons")
public class HealthController 
{
	@Autowired
	HealthService healthService;

	@RequestMapping(value = "/all",method =RequestMethod.GET)
	public Map<String, Person> getAll() {
		return healthService.getAll();
	}

	@RequestMapping(value = "{id}",method = RequestMethod.GET)
	public Person getPerson(@PathVariable("id") String id) {
		return healthService.getPerson(id);
	}

}
