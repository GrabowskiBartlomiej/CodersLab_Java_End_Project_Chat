package pl.coderslab.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pl.coderslab.app.AppConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // create spring context
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();

        // configure spring context
        context.register(AppConfig.class);
        context.setServletContext(servletContext);

        // create DispatcherServlet
        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));

        // configure DispatcherServlet
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");


    }
}
