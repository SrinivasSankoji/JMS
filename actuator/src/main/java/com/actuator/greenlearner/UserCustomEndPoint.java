package com.actuator.greenlearner;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "userDetails")
public class UserCustomEndPoint {

	 @ReadOperation
	    public UserAppInfo userAppInfo() {
	        Map<String, Object> details = new LinkedHashMap<>();
	        details.put("App Location", "!!MARS!!");
	        details.put("Status", "Reporting From Mars. All Good!!");
	        UserAppInfo health = new UserAppInfo();
	        health.setHealthDetails(details);
	        return health;
	    }

	    @ReadOperation
	    public String userAppEndPointByName(@Selector String name) {
	        return "This is user management application, reporting from Mars!! All OK !!" + name;
	    }

	    @WriteOperation
	    public void writeOperation(@Selector String name) {
	        //perform write operation
	        // this is the POST type endpoint
	    }
	    @DeleteOperation
	    public void deleteOperation(@Selector String name){
	        //delete operation
	        // this is the DELETE type endpoint
	    }
}
