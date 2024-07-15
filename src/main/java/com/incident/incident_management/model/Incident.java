package com.incident.incident_management.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Incident {
    @Id
    @GeneratedValue
    private UUID incidentId;

    public enum Type {
        INDIVIDUAL,
        ENTERPRISE,
        GOVERNMENT
    }

    public enum Status {
        OPEN,
        IN_PROGRESS,
        CLOSED
    }

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    @ManyToOne
    private User user;

    private String incidentNo;

    private String incidentDetail;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Incident(User user, String incidentNo, String incidentDetail, Type type, Status status, Priority priority) {
        this.user = user;
        this.incidentNo = incidentNo;
        this.incidentDetail = incidentDetail;
        this.type = type;
        this.status = status;
        this.priority = priority;
    }

    public Incident() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIncidentNo() {
        return incidentNo;
    }

    public void setIncidentNo(String incidentNo) {
        this.incidentNo = incidentNo;
    }

    public String getIncidentDetail() {
        return incidentDetail;
    }

    public void setIncidentDetail(String incidentDetail) {
        this.incidentDetail = incidentDetail;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public UUID getIncidentId() {
        return incidentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
