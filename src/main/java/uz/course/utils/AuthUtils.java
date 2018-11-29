package uz.course.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import uz.course.config.ApplicationContextProvider;
import uz.course.hibernate.domain._User;
import uz.course.jwt.UserPrincipal;
import uz.course.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AuthUtils {
    public static void login(_User user, HttpServletRequest request, String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (roles.length < 1) {
            authorities.addAll(user.getRoles());
        } else {
            authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(UserPrincipal.create(user), user.getPassword(), authorities);
        auth.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public static List<String> getCurrentUserRoles() {
        return ApplicationContextProvider.applicationContext.getBean(UserService.class)
                .getCurrentUserRoles();
    }
}