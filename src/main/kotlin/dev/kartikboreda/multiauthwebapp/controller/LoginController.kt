package dev.kartikboreda.multiauthwebapp.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
open class LoginController {

    @GetMapping("/login")
    open fun login(request: HttpServletRequest, model: Model): String {
        val error  = request.getParameter("error");
        val logout = request.getParameter("logout");

        if (error != null) model.addAttribute("error", "Invalid username and password!")
        if (logout != null) model.addAttribute("logout", "You have been logged out")

        val csrf =  request.getAttribute(CsrfToken::class.java.name)
        if (csrf != null ) model.addAttribute("csrf", csrf)

        return "pages/login";
    }

}
