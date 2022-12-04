package com.example.demo6new.domain.form;

import com.example.demo6new.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceForm {

    private String id;
    private String name;
    private String httpMethod;
    private int orderNum;
    private String type;
    private Set<Role> roleSet;


}
