package dev.kartikboreda.multiauthwebapp.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class HomeController {


    @GetMapping("/home")
    fun home(authentication: Authentication, request: HttpServletRequest, model: Model): String {
        val userDetails = authentication.principal as UserDetails
        model.addAttribute("username", userDetails.username)

        val csrf = request.getAttribute(CsrfToken::class.java.name) as CsrfToken
        model.addAttribute("csrf", csrf)

        return "pages/home"
    }

    @GetMapping("/")
    fun admin(): String = "redirect:/home"
}
