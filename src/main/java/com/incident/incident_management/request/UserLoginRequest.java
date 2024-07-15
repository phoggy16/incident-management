package com.incident.incident_management.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserLoginRequest(@JsonProperty("email_id") String emailId, @JsonProperty("password") String password) {
}
