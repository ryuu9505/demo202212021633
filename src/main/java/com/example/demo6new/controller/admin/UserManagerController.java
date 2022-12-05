package com.example.demo6new.controller.admin;

import com.example.demo6new.domain.Account;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.AccountForm;
import com.example.demo6new.domain.form.AccountModifyForm;
import com.example.demo6new.service.AccountService;
import com.example.demo6new.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserManagerController {
	
	private final AccountService accountService;
	private final RoleService roleService;


}
