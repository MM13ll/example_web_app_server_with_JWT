package uz.course.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import uz.course.config.ApplicationContextProvider;
import uz.course.hibernate.domain._UserSession;
import uz.course.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private JwtTokenProvider tokenProvider;
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {

        if (tokenProvider == null)
            tokenProvider = ApplicationContextProvider.applicationContext.getBean(JwtTokenProvider.class);
        if (userService == null)
            userService = ApplicationContextProvider.applicationContext.getBean(UserService.class);

        String jwt = tokenProvider.generateToken(authentication);
        Cookie cookie = new Cookie("access_token", "Bearer " + jwt);
        cookie.setMaxAge(3600);
        cookie.setVersion(1);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        httpServletResponse.addCookie(cookie);
        _UserSession userSession = userService.createSession(authentication, request, cookie);

    }
}