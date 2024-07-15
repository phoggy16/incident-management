package com.incident.incident_management.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserLoginResponse(@JsonProperty("access_token") String accessToken, @JsonProperty("refresh_token") String refreshToken) {
}
