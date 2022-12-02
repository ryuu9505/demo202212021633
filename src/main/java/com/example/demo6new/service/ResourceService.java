package com.example.demo6new.service;

import com.example.demo6new.domain.Resource;
import com.example.demo6new.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public Resource getResource(long id) {
        return resourceRepository.findById(id).orElse(new Resource());
    }

    public List<Resource> getResources() {
        return resourceRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    public void createResource(Resource resource){
        resourceRepository.save(resource);
    }

    public void deleteResource(long id) {
        resourceRepository.deleteById(id);
    }

}