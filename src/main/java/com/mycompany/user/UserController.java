package com.mycompany.user;

import com.mycompany.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    @Autowired private UserRepository repo;

    // handle the http register request and return signup_form.html
    // save the object user
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    // handle the http register request and return register_success.html
    @PostMapping("/process_register")
    public String processRegister(User user) {
        // encrypt password for new account
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);

        return "register_success";
    }
}
