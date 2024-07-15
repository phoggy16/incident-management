package com.incident.incident_management.service;

import com.incident.incident_management.exception_handling.CustomException;
import com.incident.incident_management.model.Incident;
import com.incident.incident_management.model.User;
import com.incident.incident_management.repository.IncidentRepository;
import com.incident.incident_management.repository.UserRepository;
import com.incident.incident_management.request.IncidentCreateRequest;
import com.incident.incident_management.request.IncidentListResponse;
import com.incident.incident_management.request.IncidentUpdateRequest;
import com.incident.incident_management.response.IncidentGetResponse;
import com.incident.incident_management.response.PagingResponse;
import com.incident.incident_management.util.StringUtil;
import com.incident.incident_management.util.UserUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IncidentServiceImpl implements IncidentService{
    private final UserUtil userUtil;

    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;

    public IncidentServiceImpl(UserUtil userUtil, IncidentRepository incidentRepository,
                               UserRepository userRepository) {
        this.userUtil = userUtil;
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createIncident(IncidentCreateRequest incidentCreateRequest) {
        User user = userUtil.getLoggedInUser();

        String incidentNo=getUniqueIncidentNoPerUser(user);

        Incident incident =new Incident(
                user,
                incidentNo,
                incidentCreateRequest.incidentDetail(),
                Incident.Type.valueOf(incidentCreateRequest.incidentType()),
                Incident.Status.OPEN,
                Incident.Priority.valueOf(incidentCreateRequest.priority())
        );

        incidentRepository.save(incident);
    }

    @Override
    public PagingResponse getUserIncidents(int size, int pageNo) {
        Pageable paging =
                PageRequest.of(pageNo, size);

        User user = userUtil.getLoggedInUser();

        Page<Incident> incidentPage = incidentRepository.findByUser(user, paging);

        List<IncidentListResponse> incidentListResponses =incidentPage.getContent().stream()
                        .map(incident-> new IncidentListResponse(
                                incident.getIncidentId().toString(),
                                incident.getIncidentNo(),
                                incident.getIncidentDetail(),
                                incident.getType().name(),
                                incident.getStatus().name(),
                                incident.getPriority().name(),
                                incident.getCreatedAt())).toList();

        return new PagingResponse(incidentListResponses,(int) incidentPage.getTotalElements(),incidentPage.getTotalPages());
    }

    @Override
    public void updateIncident(String incidentId, IncidentUpdateRequest incidentUpdateRequest) {
        User user = userUtil.getLoggedInUser();
        Incident incident = incidentRepository.findByIncidentIdAndUser(UUID.fromString(incidentId), user)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST,"Invalid id passed"));

        if(incident.getStatus().name().equalsIgnoreCase(Incident.Status.CLOSED.name())){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Incident is closed");
        }

        incident.setIncidentDetail(incidentUpdateRequest.incidentDetail());
        incident.setPriority(Incident.Priority.valueOf(incidentUpdateRequest.priority()));
        incident.setStatus(Incident.Status.valueOf(incidentUpdateRequest.status()));

        incidentRepository.save(incident);
    }

    @Override
    public IncidentGetResponse getIncidentByIncidentNo(String incidentNo) {
        User user = userUtil.getLoggedInUser();

        Incident incident = incidentRepository.findByIncidentNoAndUser(incidentNo,user).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, "Invalid incident No")
        );

        return new IncidentGetResponse(incident.getIncidentNo(),
                incident.getIncidentDetail(),
                incident.getType().name(),
                incident.getStatus().name(),
                incident.getPriority().name(),
                incident.getCreatedAt());
    }

    private String getUniqueIncidentNoPerUser(User user){
        String incidentNo;
        do{
            incidentNo="RMG" + StringUtil.getRandomNumberString() + LocalDateTime.now().getYear();
        }while (isIncidentNoInUser(incidentNo,user));

        return incidentNo;

    }
    private boolean isIncidentNoInUser(String incidentNo, User user){
        Optional<Incident> incident = incidentRepository.findByIncidentNoAndUser(incidentNo,user);
        return incident.isPresent();
    }
}
