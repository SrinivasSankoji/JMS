package com.actuator.calicoder;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator1 extends AbstractHealthIndicator{

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		builder.up().withDetail("DCIMCORE","UP")
					.build();
	}

}
