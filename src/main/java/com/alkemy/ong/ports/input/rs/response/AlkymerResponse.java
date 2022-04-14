package com.alkemy.ong.ports.input.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlkymerResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

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

    @JsonProperty("skills")
    private List<SkillResponse> skills = null;
}
