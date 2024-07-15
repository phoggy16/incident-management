package com.incident.incident_management.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record IncidentListResponse(@JsonProperty("incident_id") String incidentId,
                                   @JsonProperty("incident_no") String incidentNo,
                                   @JsonProperty("incident_detail") String incidentDetail,
                                   @JsonProperty("type") String type,
                                   @JsonProperty("status") String status,
                                   @JsonProperty("priority") String priority,
                                   @JsonProperty("created_at")LocalDateTime createdAt) {
}
