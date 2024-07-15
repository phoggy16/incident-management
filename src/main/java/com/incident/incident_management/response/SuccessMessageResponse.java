package com.incident.incident_management.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SuccessMessageResponse(@JsonProperty("message") String message) {
}
