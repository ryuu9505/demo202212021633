package com.example.demo6new.domain.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleModifyForm {

    @NotBlank
    private String name;

}
