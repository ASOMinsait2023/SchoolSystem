package com.minsait.Clasroom.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class StudentProfileDTO {

    public String first_name;
    public String last_name;
    public String email;

}
