package com.example.demo6new.controller.admin;

import com.example.demo6new.domain.Account;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.AccountForm;
import com.example.demo6new.domain.form.AccountModifyForm;
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
        AccountForm form = accountService.getAccountForm(id);
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("account", form);
        model.addAttribute("roleList", roleList); // todo refactor: roleList to roles

        return "admin/user/detail";
    }

    @GetMapping("/admin/accounts/delete/{id}") // todo feat: modify to use DELETE method
    public String deleteAccount(@PathVariable(value = "id") Long id) {
        accountService.deleteAccount(id);

        return "redirect:/admin/users";
    }
}
