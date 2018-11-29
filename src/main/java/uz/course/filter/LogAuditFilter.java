package uz.course.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;
import uz.course.config.ApplicationContextProvider;
import uz.course.service.SessionUtils;
import uz.course.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

@WebFilter(filterName = "logAuditFilter", urlPatterns = "/*", asyncSupported = true)
public class LogAuditFilter extends HttpServlet implements Filter {
    private static final Logger log = LogManager.getLogger(LogAuditFilter.class);
    private SessionLocaleResolver localeResolver;

    public SessionLocaleResolver getLocaleResolver() {
        if (localeResolver == null)
            localeResolver = ApplicationContextProvider.applicationContext.getBean(SessionLocaleResolver.class);
        return localeResolver;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Date start = new Date();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI().toLowerCase();
        if (url.matches(CharactersEncodingFilter.exclude)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Cookie cookie = WebUtils.getCookie(request, "lang");
        if (cookie != null) {
            SessionUtils.getInstance().setLanguage(cookie.getValue());
        }
        if (StringUtils.isEmpty(SessionUtils.getInstance().getLanguage()))
            SessionUtils.getInstance().setLanguage("uz");
        getLocaleResolver().setLocale(request, response, Locale.forLanguageTag(SessionUtils.getInstance().getLanguage()));
        request.setAttribute("lang", SessionUtils.getInstance().getLanguage());

        SessionUtils.getInstance().setSessionId(ServerUtils.getAccessToken(request));
        filterChain.doFilter(servletRequest, servletResponse);

        Date end = new Date();
        long elapsedTime = end.getTime() - start.getTime();
        log.info("->->Request = [ " + request.getRequestURI() + " ] Elapsed time to proceed this request = " + elapsedTime);
    }
}