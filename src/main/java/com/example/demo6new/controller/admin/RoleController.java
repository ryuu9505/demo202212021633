package com.example.demo6new.controller.admin;

import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.RoleCreateForm;
import com.example.demo6new.domain.form.RoleForm;
import com.example.demo6new.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

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
		RoleForm form = roleService.getRoleForm(id);
		model.addAttribute("role", form);

		return "admin/role/detail";
	}

	@GetMapping("/admin/roles/delete/{id}") // todo refactor: to use DELETE method
	public String deleteResources(@PathVariable Long id) {
		roleService.deleteRole(id);
		return "redirect:/admin/resources";
	}
}
