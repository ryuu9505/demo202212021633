package com.example.demo6new.controller.admin;

import com.example.demo6new.domain.Account;
import com.example.demo6new.domain.Resource;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.*;
import com.example.demo6new.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import com.example.demo6new.service.AccountService;
import com.example.demo6new.service.ResourceService;
import com.example.demo6new.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AdminController { // todo refactor: 'resources' on urls to 'resource'

    private final ModelMapper modelMapper;
    private final UrlFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    private final ResourceService resourcesService;
    private final RoleService roleService;
    private final AccountService accountService;

    @GetMapping("/admin")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/admin/accounts")
    public String getAccounts(Model model) {

        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);

        return "admin/user/list";
    }

    @PostMapping("/admin/account/{id}")
    public String modifyAccount(@PathVariable Long id, AccountModifyForm form) {
        accountService.modifyAccount(id, form);

        return "redirect:/admin/account/" + id;
    }

    @GetMapping("/admin/accounts/{id}")
    public String getAccount(@PathVariable Long id, Model model) {
        Account form = accountService.getAccount(id);
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("form", form);
        model.addAttribute("roles", roleList);

        return "admin/user/detail";
    }

    @GetMapping("/admin/accounts/delete/{id}") // todo feat: modify to use DELETE method
    public String deleteAccount(@PathVariable(value = "id") Long id) {
        accountService.deleteAccount(id);

        return "redirect:/admin/users";
    }





    @GetMapping("/admin/roles")
    public String getRoles(Model model) {
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roles", roleList);
        return "admin/role/list";
    }

    @GetMapping("/admin/roles/register")
    public String createRoleForm(Model model) { // todo check
        RoleForm form = new RoleForm();
        model.addAttribute("role", form);
        return "admin/role/detail";
    }

    @PostMapping("/admin/roles")
    public String createRole(RoleCreateForm form) {
        roleService.createRole(form);
        return "redirect:/admin/roles";
    }

    @GetMapping("/admin/roles/{id}")
    public String getRole(@PathVariable Long id, Model model) {
        Role role = roleService.getRole(id);
        model.addAttribute("role", role);

        return "admin/role/detail";
    }

    @GetMapping("/admin/roles/delete/{id}") // todo refactor: to use DELETE method
    public String deleteResources(@PathVariable Long id) {
        roleService.deleteRole(id);
        return "redirect:/admin/resources";
    }



    @GetMapping("/admin/resources")
    public String resourcesList(Model model) {

        List<Resource> resourceList = resourcesService.getResourceList();
        model.addAttribute("resources", resourceList);

        return "admin/resource/list";
    }

    @GetMapping("/admin/resources/{id}")
    public String getResource(@PathVariable Long id, Model model) {

        /* todo modify with html file (move logics to service: getResourceForm())*/
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roles", roleList);

        Resource resource = resourcesService.getResource(id);
        model.addAttribute("resource", resource);

        return "admin/resource/detail";
    }

    @PostMapping("/admin/resources/{id}")
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
