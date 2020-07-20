package com.actuator.greenlearner;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class CustomHealthIndicator3 implements HealthIndicator{

	@Override
	public Health health() {
		int errorCode=check();
		if (errorCode != 1) {
			return Health.down().withDetail("Error Code", errorCode).build();
		}
		return Health.up().build();
	}

	private int check() {
		return 1;
	}

}
