package com.spring.practice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {
    private String fullName;

    @Email
    private String email;

    @Size(min =10, max = 10)
    private String phoneNumber;


}
