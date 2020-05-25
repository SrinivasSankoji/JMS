package com.example.demo.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BcryptController 
{
	public static String hash="";
	
	@GetMapping("/bcrypt/{pass}")
	public String getHashedPassword(@PathVariable("pass") String pass)
	{
		hash=hashPassword(pass);
		return hash;
	}
	
	@GetMapping("/encrypt/{password}")
	public String validatePassword(@PathVariable("password") String password)
	{
		if (!BCrypt.checkpw(password, hash)) {
			return "Wrong Password!";
        }
		return "Success";
		
	}
	
	private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
	
	@GetMapping("/getJenkins")
	public String getJenkins()
	{
		return "Automated Deployment";
	}

}
