package com.actuator.greenlearner;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.health.model.Greeting;

@Component
@Endpoint(id = "custom-endpoint")
public class CustomEndPoint
{
	private final AtomicLong counter=new AtomicLong();
	
	@ReadOperation
	public Greeting customEndPoint(@RequestParam(defaultValue = "Bhaumik") String name)
	{
		return new Greeting(counter.incrementAndGet(),"Hello "+name);
	}
}
