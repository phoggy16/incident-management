package com.incident.incident_management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record IncidentCreateRequest(@NotBlank @JsonProperty("incident_type") String incidentType,
                                    @NotBlank @JsonProperty("priority") String priority,
                                    @NotBlank @JsonProperty("incident_detail") String incidentDetail) {
}
