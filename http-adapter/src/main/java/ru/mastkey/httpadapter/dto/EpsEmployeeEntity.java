package ru.mastkey.httpadapter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpsEmployeeEntity {
    private UUID id;
    private UUID ipropuskId;
    private String name;

    @JsonCreator
    public EpsEmployeeEntity(
            @JsonProperty("id") String id,
            @JsonProperty("ipropuskId") String ipropuskId,
            @JsonProperty("name") String name
    ) {
        this.id = id != null ? UUID.fromString(id) : null;
        this.ipropuskId = ipropuskId != null ? UUID.fromString(ipropuskId) : null;
        this.name = name;
    }
}
