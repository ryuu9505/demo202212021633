package com.example.demo6new.service;

import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.RoleCreateForm;
import com.example.demo6new.domain.form.RoleForm;
import com.example.demo6new.domain.form.RoleModifyForm;
import com.example.demo6new.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    // todo other create methods to return that object
    // todo hierarchy logic
    public Role createRole(RoleCreateForm form) {
        Role role = modelMapper.map(form, Role.class);
        return roleRepository.save(role);
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
        return roleRepository.findById(id).orElseThrow();
    } // todo EH

    public RoleForm getRoleForm(long id) {
        return modelMapper.map(roleRepository.findById(id), RoleForm.class);
    }

    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    public Set<Role> getDefaultRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER")); // todo exception handling
        return roles;
    }

}
