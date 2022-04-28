package com.alkemy.ong.ports.input.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("content")
    private String content;

    @DateTimeFormat(
            iso = ISO.DATE_TIME
    )
    @JsonProperty("start_date")
    private Date startDate;

}
