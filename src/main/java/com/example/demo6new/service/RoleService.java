package com.example.demo6new.service;

import com.example.demo6new.domain.Resource;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.ResourceModifyForm;
import com.example.demo6new.domain.form.RoleCreateForm;
import com.example.demo6new.domain.form.RoleModifyForm;
import com.example.demo6new.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    // todo if already exist same name role
    // todo hierarchy logic
    public void createRole(RoleCreateForm form) {
        Role role = modelMapper.map(form, Role.class);
        roleRepository.save(role);
    }

    // todo hierarchy logic
    public void modifyRole(Long id, RoleModifyForm form){
        Role role = roleRepository.findById(id).orElseThrow();
        modelMapper.map(form, role);
        roleRepository.save(role);
    }

    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }

    public Role getRole(long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

}
