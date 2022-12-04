package com.example.demo6new.domain.form;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleCreateForm {

    // todo unique
    @NotBlank
    private String name;

}
