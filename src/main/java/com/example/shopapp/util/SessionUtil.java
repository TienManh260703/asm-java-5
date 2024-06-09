package com.example.shopapp.util;

import com.example.shopapp.model.Staff;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
