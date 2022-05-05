package com.alkemy.ong.ports.input.rs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlkymerRequest {

    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotNull
    @DateTimeFormat(
            iso = ISO.DATE_TIME
    )
    @JsonProperty("start_date")
    private Date startDate;

    @DateTimeFormat(
            iso = ISO.DATE_TIME
    )
    @JsonProperty("end_date")
    private Date endDate;

    @Valid
    @JsonProperty("skills")
    private List<SkillRequest> skills;
}
