package com.incident.incident_management.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PagingResponse(
        @JsonProperty("list") List<?> listingResponse,
        @JsonProperty("total_elements") int totalElements,
        @JsonProperty("total_pages") int totalPages) {}
