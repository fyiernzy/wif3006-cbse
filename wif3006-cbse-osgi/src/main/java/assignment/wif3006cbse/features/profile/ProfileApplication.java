package assignment.wif3006cbse.features.profile;

import org.osgi.service.component.annotations.Component;

import javax.ws.rs.core.Application;

@Component(
    service = Application.class,
    property = {
        "osgi.jaxrs.application.base=/",
        "osgi.jaxrs.name=profile"
    }
)
public class ProfileApplication extends Application {

}
