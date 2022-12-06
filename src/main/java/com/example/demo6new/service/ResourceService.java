package com.example.demo6new.service;

import com.example.demo6new.domain.Resource;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.ResourceCreateForm;
import com.example.demo6new.domain.form.ResourceModifyForm;
import com.example.demo6new.domain.form.RoleModifyForm;
import com.example.demo6new.repository.ResourceRepository;
import com.example.demo6new.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ResourceService {

    private final ModelMapper modelMapper;
    private final ResourceRepository resourceRepository;
    private final RoleRepository roleRepository;
    public void createResource(ResourceCreateForm form){
        Resource resource = modelMapper.map(form, Resource.class);
        resource.setRoles(convertToRoles(form.getRoleNameList()));
        resourceRepository.save(resource);
    }
    public void modifyResource(Long id, ResourceModifyForm resourceForm){
        Resource resource = resourceRepository.findById(id).orElseThrow();
        modelMapper.map(resourceForm, resource);
        resource.setRoles(convertToRoles(resourceForm.getRoleNameList()));
        resourceRepository.save(resource);
    }

    private Set<Role> convertToRoles(List<String> form) {
        Set<Role> roles = new HashSet<>();
        form.forEach(roleName -> {
            roles.add(roleRepository.findByName(roleName)); // todo exception handling
        });
        return roles;
    }

    public Resource getResource(long id) {
        return resourceRepository.findById(id).get(); // todo EH
    }

    public ResourceModifyForm getResourceForm(long id) {
        Resource resource = resourceRepository.findById(id).get();
        ResourceModifyForm resourceModifyForm = modelMapper.map(resource, ResourceModifyForm.class);
        resourceModifyForm.setRoleNameList(
                resource.getRoles().stream().map(role ->
                        role.getName()).collect(Collectors.toList()));
        return resourceModifyForm;
    }
    public List<Resource> getResourceList() {
        return resourceRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    public void deleteResource(long id) {
        resourceRepository.deleteById(id);
    }



}