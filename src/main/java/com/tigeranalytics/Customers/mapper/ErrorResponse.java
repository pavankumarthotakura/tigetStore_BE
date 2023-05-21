package com.tigeranalytics.Customers.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    boolean error;
    String message;
    boolean isJwtExpired;
}
