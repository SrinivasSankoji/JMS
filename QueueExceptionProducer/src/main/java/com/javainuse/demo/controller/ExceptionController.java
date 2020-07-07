package com.javainuse.demo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.demo.model.Employee;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@RequestMapping("/producer")
	public String producer(@RequestParam("empName") String empName,
			@RequestParam("empId") String empId,
			@RequestParam("salary") int salary)
	{
		Employee emp=new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		emp.setSalary(salary);

		rabbitTemplate.convertAndSend("javainuseExchange", "javainuse", emp);
		return "Message Sent to the RabitMQ Successfully";
	}

}
