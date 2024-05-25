package com.example.shopapp.util;

import com.example.shopapp.dto.request.LoginRequest;
import com.example.shopapp.model.Staff;
import com.example.shopapp.service.IStaffService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionUtil {

    HttpSession session;
    public void set( Object value) {
        session.setAttribute("account", value);
    }

    public Staff get() {
        return (Staff) session.getAttribute("account");
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }
}
