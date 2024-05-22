package com.example.shopapp.exception;

import jakarta.servlet.jsp.JspTagException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class })
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception , Model model){
        model.addAttribute("error", "Http Request Method Not Supported Exception");
        model.addAttribute("message", exception.getMessage());
        return "/exception/index";
    }

    @ExceptionHandler(JspTagException.class)
    public String handleJspTagException(JspTagException ex, Model model) {
        model.addAttribute("error", "JSP Tag Exception");
        model.addAttribute("message", ex.getMessage());
        return "/exception/index";
    }
}
