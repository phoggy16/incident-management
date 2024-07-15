package com.incident.incident_management.exception_handling;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record CustomError(HttpStatus status, String message
) {
}
