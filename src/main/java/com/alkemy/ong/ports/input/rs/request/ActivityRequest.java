package com.alkemy.ong.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest  {

    @NotBlank(message = "The name must not be empty")
    private  String name;

    @NotBlank(message = "The content must not be empty")
    private  String content;

    private  String image;
}
