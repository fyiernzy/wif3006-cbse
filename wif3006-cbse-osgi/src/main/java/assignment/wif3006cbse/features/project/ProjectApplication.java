package assignment.wif3006cbse.features.project;

import org.osgi.service.component.annotations.Component;

import javax.ws.rs.core.Application;

/**
 * OSGI JAX-RS Application for the Project module.
 * Registers REST endpoints for project management, applications, deliverables, and reviews.
 */
@Component(
    service = Application.class,
    property = {
        "osgi.jaxrs.application.base=/",
        "osgi.jaxrs.name=project"
    }
)
public class ProjectApplication extends Application {

}
