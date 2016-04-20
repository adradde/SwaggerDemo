package com.epam.lisovyn;

import com.epam.lisovyn.ws.UserWS;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/rest")
public class AppRestConfiguration extends Application {
    private Set<Object> webServices = new HashSet<Object>();

    public AppRestConfiguration() {
        initSwaggerConfiguration();
        initWebServices();
}

    private void initSwaggerConfiguration() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080/SwaggerDemo");
        beanConfig.setBasePath("api/rest");
        beanConfig.setResourcePackage("com.epam.lisovyn.ws");
        beanConfig.setScan(true);
    }

    private void initWebServices() {
        webServices.add(new UserWS());
//        webServices.add(new ProductWS());
        webServices.add(new ApiListingResource());
        webServices.add(new SwaggerSerializers());
    }

    @Override
    public Set<Object> getSingletons() {
        return webServices;
    }
}
