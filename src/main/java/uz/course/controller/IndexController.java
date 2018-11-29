package uz.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.course.hibernate.dao.UserDao;
import uz.course.hibernate.domain._User;
import uz.course.jwt.UserSummary;
import uz.course.service.SessionUtils;
import uz.course.service.UserService;
import uz.course.to.ApiResponse;
import uz.course.to.ChangePassword;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @GetMapping("")
    public String index(Map<String, Object> model) {
        return "landing";
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        model.put("page", "login");
        return "login";
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @GetMapping("/sign-up")
    public String signUp(Map<String, Object> model) {
        return "login";
    }

    @ResponseBody
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @GetMapping(value = "/user/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserSummary getCurrentUser() {
        _User user = SessionUtils.getInstance().getUser();
        if (user == null) return new UserSummary(null, null, null, null, null);
        List<String> roles = SessionUtils.getInstance().getRoles();

        String uri = roles.size() > 1
                ? "/choose"
                : roles.contains("ROLE_ADMIN")
                ? "/admin"
                : roles.contains("ROLE_TEACHER")
                ? "/teacher"
                : "/cabinet";

        UserSummary userSummary = new UserSummary(user.getId(),
                user.getPhone(), user.getShortName(), "", uri);
        return userSummary;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @GetMapping(value = "/change-password")
    public String changePassword(Map<String, Object> model) {
        model.put("page", "change-password");
        model.put("user", SessionUtils.getInstance().getUser());
        return "login";
    }

    @PostMapping(value = "/change-password", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse changePassword(ChangePassword changePassword) {
        userService.changePassword(changePassword);
        return new ApiResponse(true, "Парол янгиланди");
    }
}