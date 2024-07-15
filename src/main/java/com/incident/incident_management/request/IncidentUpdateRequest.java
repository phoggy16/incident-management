package com.incident.incident_management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record IncidentUpdateRequest(@NotBlank @JsonProperty("incident_detail") String incidentDetail,
                                   @NotBlank @JsonProperty("priority") String priority,
                                   @NotBlank @JsonProperty("status") String status) {
}
