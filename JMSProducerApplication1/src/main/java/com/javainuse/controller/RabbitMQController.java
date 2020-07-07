package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Employee;
import com.javainuse.service.RabbitMQService;

@RestController
@RequestMapping(value = "/javainuse")
public class RabbitMQController {
	
	@Autowired
	RabbitMQService rabbitMQService;

	@GetMapping(value = "/producer/{empName}/{empId}")
	public String producer(@PathVariable("empName") String empName, @PathVariable("empId") String empId) {

		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		rabbitMQService.send(emp);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}
	
	@GetMapping("/getJenkins")
	public String getJenkins()
	{
		return "Automated Deployment";
	}
}
