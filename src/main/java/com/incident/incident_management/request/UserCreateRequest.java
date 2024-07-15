package com.incident.incident_management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public record UserCreateRequest(@NotBlank @JsonProperty("name") String name,
                                @Email @NotBlank @JsonProperty("email_id") String emailId,
                                @NotNull @Range(min = 1000000000, max = 1999999999) @JsonProperty("phone_number") Long phoneNumber,
                                @NotBlank @JsonProperty("address") String address,
                                @NotBlank @JsonProperty("city") String city,
                                @NotBlank @JsonProperty("country") String country,
                                @NotNull @Range(min = 1, max = 9999999) @JsonProperty("pin_code") Integer pinCode,
                                @NotBlank @JsonProperty("password") String password) {
}
