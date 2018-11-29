package uz;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import uz.course.config.*;

public class Main extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class, HibernateConfig.class,
                CachingConfig.class, SpringAsyncConfig.class,
                SpringSchedulingConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}