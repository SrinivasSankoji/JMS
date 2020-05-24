package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkinsController 
{
	@GetMapping("/getJenkins")
	public String getJenkins()
	{
		return "Automated Deployment";
	}

}
