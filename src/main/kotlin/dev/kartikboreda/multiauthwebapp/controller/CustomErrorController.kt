package dev.kartikboreda.multiauthwebapp.controller

import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class CustomErrorController: ErrorController {

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
        if (status != null) {
            val statusCode = Integer.valueOf(status.toString())
            if (statusCode == 401) {
                return "redirect:/login"
            }
            if (statusCode == 403) {
                return "redirect:/accessDenied"
            }
            if (statusCode == 404) {
                return "redirect:/404"
            }
            if (statusCode == 500) {
                return "redirect:/500"
            }
        }

        return "error"
    }
}
