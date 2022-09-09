package web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }


    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        super.onStartup(context);
        registerCharacterEncodingFilter(context);
    }

    private void registerCharacterEncodingFilter(ServletContext context) {
        EnumSet<DispatcherType> dispatcherTypes =
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);

        CharacterEncodingFilter characterEncodingFilter =
                new CharacterEncodingFilter("UTF-8", true, true);

        context.addFilter("encoding", characterEncodingFilter)
                .addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}
