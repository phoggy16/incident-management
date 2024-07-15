package com.incident.incident_management.controller;

import com.incident.incident_management.exception_handling.CustomException;
import com.incident.incident_management.model.Incident;
import com.incident.incident_management.request.IncidentCreateRequest;
import com.incident.incident_management.request.IncidentUpdateRequest;
import com.incident.incident_management.response.IncidentGetResponse;
import com.incident.incident_management.response.PagingResponse;
import com.incident.incident_management.response.SuccessMessageResponse;
import com.incident.incident_management.service.IncidentService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/incident")
@Validated
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public SuccessMessageResponse createIncident(@Valid @RequestBody IncidentCreateRequest incidentCreateRequest){
        boolean isTypeValid = EnumUtils.isValidEnum(Incident.Type.class, incidentCreateRequest.incidentType());
        if(!isTypeValid){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Enter Valid type. Possible values "+ Arrays.toString(Incident.Type.values()));
        }
        boolean isPriorityValid = EnumUtils.isValidEnum(Incident.Priority.class, incidentCreateRequest.priority());
        if(!isPriorityValid){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Enter Valid priority. Possible values "+ Arrays.toString(Incident.Priority.values()));
        }

        incidentService.createIncident(incidentCreateRequest);
        return new SuccessMessageResponse("Incident Created Successfully");
    }

    @PatchMapping("/{incident_id}")
    public SuccessMessageResponse editIncident(@PathVariable("incident_id") String incidentId,@Valid @RequestBody IncidentUpdateRequest incidentUpdateRequest){
        boolean isStatusValid = EnumUtils.isValidEnum(Incident.Status.class, incidentUpdateRequest.status());
        if(!isStatusValid){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Enter Status type. Possible values "+ Arrays.toString(Incident.Status.values()));
        }
        boolean isPriorityValid = EnumUtils.isValidEnum(Incident.Priority.class, incidentUpdateRequest.priority());
        if(!isPriorityValid){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Enter Valid priority. Possible values "+ Arrays.toString(Incident.Priority.values()));
        }

        incidentService.updateIncident(incidentId,incidentUpdateRequest);
        return new SuccessMessageResponse("Incident Updated Successfully");
    }

    @GetMapping
    public PagingResponse getUserIncident(
            @RequestParam(defaultValue = "0", name = "page") Integer pageNo,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize
    ){
        return incidentService.getUserIncidents(pageSize,pageNo);
    }

    @GetMapping("/{incident_no}")
    public IncidentGetResponse getByIncidentNo(@PathVariable("incident_no") String incidentNo){
        return incidentService.getIncidentByIncidentNo(incidentNo);
    }
}
