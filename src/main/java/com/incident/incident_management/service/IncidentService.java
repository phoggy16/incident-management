package com.incident.incident_management.service;

import com.incident.incident_management.request.IncidentCreateRequest;
import com.incident.incident_management.request.IncidentListResponse;
import com.incident.incident_management.request.IncidentUpdateRequest;
import com.incident.incident_management.response.IncidentGetResponse;
import com.incident.incident_management.response.PagingResponse;

import java.util.List;

public interface IncidentService {
    void createIncident(IncidentCreateRequest incidentCreateRequest);

    PagingResponse getUserIncidents(int pazeSize, int pageNo);

    void updateIncident(String incidentId, IncidentUpdateRequest incidentUpdateRequest);

    IncidentGetResponse getIncidentByIncidentNo(String incidentNo);

}
