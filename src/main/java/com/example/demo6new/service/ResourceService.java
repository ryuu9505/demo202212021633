package com.example.demo6new.service;

import com.example.demo6new.domain.Resource;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.ResourceCreateForm;
import com.example.demo6new.domain.form.ResourceModifyForm;
import com.example.demo6new.repository.ResourceRepository;
import com.example.demo6new.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void modifyResource(Long id, ResourceModifyForm form){
        Resource resource = resourceRepository.findById(id).orElseThrow();
        modelMapper.map(form, resource);
        resource.setRoles(convertToRoles(form.getRoleNameList()));
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
        return resourceRepository.findById(id).orElseThrow(); // todo EH
    }

    public List<Resource> getResourceList() {
        return resourceRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    public void deleteResource(long id) {
        resourceRepository.deleteById(id);
    }



}