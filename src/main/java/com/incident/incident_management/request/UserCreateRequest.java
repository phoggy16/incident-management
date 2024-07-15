package com.incident.incident_management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public record UserCreateRequest(@NotBlank @JsonProperty("name") String name,
                                @Email @NotBlank @JsonProperty("email_id") String emailId,
                                @NotNull @Size(min = 10,max = 10)  @JsonProperty("phone_number") String phoneNumber,
                                @NotBlank @JsonProperty("address") String address,
                                @NotBlank @JsonProperty("city") String city,
                                @NotBlank @JsonProperty("country") String country,
                                @NotNull @Range(min = 1, max = 9999999) @JsonProperty("pin_code") Integer pinCode,
                                @NotBlank @JsonProperty("password") String password) {
}
