package ru.mastkey.httpadapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeIpropuskRequest {
    private String name;

    public EmployeeIpropuskRequest(EpsEmployeeEntity epsEmployeeEntity) {
        this.name = epsEmployeeEntity.getName();
    }
}
