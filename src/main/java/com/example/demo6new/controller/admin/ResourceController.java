package com.example.demo6new.controller.admin;

import com.example.demo6new.domain.Resource;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.ResourceCreateForm;
import com.example.demo6new.domain.form.ResourceForm;
import com.example.demo6new.domain.form.ResourceModifyForm;
import com.example.demo6new.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import com.example.demo6new.repository.RoleRepository;
import com.example.demo6new.service.ResourceService;
import com.example.demo6new.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ResourceController {

    private final ModelMapper modelMapper;
    private final UrlFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    private final ResourceService resourcesService;
    private final RoleService roleService;

    @GetMapping("/admin/resources")
    public String resourcesList(Model model) {

        List<Resource> resourceList = resourcesService.getResourceList();
        model.addAttribute("resources", resourceList);

        return "admin/resource/list";
    }

    @GetMapping("/admin/resource/{id}")
    public String getResource(@PathVariable Long id, Model model) {

        /* todo modify with html file (move logics to service: getResourceForm())*/
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roleList", roleList);

        Resource resource = resourcesService.getResource(id);
        ResourceForm resourceForm = modelMapper.map(resource, ResourceForm.class);
        model.addAttribute("resource", resourceForm);

        return "admin/resource/detail";
    }

    @PostMapping("/admin/resource/{id}")
    public String modifyResource(@PathVariable Long id, ResourceModifyForm form, Model model) {
        resourcesService.modifyResource(id, form);
        return "admin/resource/detail";
    }


    @GetMapping("/admin/resources/register")
    public String viewRoles(Model model) {

        /* todo modify with html file (move logics to service)*/
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roleList", roleList);

        ResourceForm resourcesForm = new ResourceForm();
        /*Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role());
        resourcesForm.setRoleSet(roleSet);*/
        model.addAttribute("resources", resourcesForm);

        return "admin/resource/detail";
    }
    
    @PostMapping("/admin/resources")
    public String createResource(ResourceCreateForm resourcesCreateForm) {

        resourcesService.createResource(resourcesCreateForm);
        filterInvocationSecurityMetadataSource.reload();

        return "redirect:/admin/resources";
    }

    @GetMapping("/admin/resources/delete/{id}") // todo; to use DELETE method
    public String removeResource(@PathVariable String id, Model model) {

        resourcesService.deleteResource(Long.valueOf(id));
        filterInvocationSecurityMetadataSource.reload();

        return "redirect:/admin/resources";
    }

}
