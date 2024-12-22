package ru.mastkey.httpadapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeIpropuskResponse {
    private UUID id;
    private String name;
}
