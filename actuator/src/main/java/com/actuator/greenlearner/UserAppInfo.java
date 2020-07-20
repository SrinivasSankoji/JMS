package com.actuator.greenlearner;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserAppInfo {

	 private Map<String, Object> healthDetails;
}
