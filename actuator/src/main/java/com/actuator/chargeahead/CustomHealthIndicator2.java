package com.actuator.chargeahead;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator2 implements HealthIndicator
{
	@Override
	public Health health()
	{
		try {
		URL url=new URL("https://www.google.com");
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code=connection.getResponseCode();
			if(code==200)
			{
				return Health.up().build();
			}
			else
			{
				return Health.down().withDetail("Error", "Service is Down").build();
			}
		} catch (IOException e) {
			return Health.down().withDetail("Error", "Service UnAvailable").build();
		}
	}
}
