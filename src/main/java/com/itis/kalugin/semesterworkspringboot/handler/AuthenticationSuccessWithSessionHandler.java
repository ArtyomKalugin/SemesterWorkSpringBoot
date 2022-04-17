package com.itis.kalugin.semesterworkspringboot.handler;

import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.model.User;
import com.itis.kalugin.semesterworkspringboot.repository.UserRepository;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessWithSessionHandler extends
        SavedRequestAwareAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException,
            ServletException {

        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        request.getSession().setAttribute("user", user);
        request.getSession().removeAttribute("authenticationFailure");

        response.sendRedirect("/info");
    }
}

