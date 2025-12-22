package assignment.wif3006cbse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(immediate = true)
public class Main {

    @Activate
    public void start() {
        System.out.println("""
                =================================================
                   WIF3006 CBSE OSGi Application Started
                =================================================
                Status: ONLINE
                Community Feature: READY
                Endpoint: http://localhost:8181/api/hello
                =================================================""");
    }

    @Deactivate
    public void stop() {
        System.out.println("""
                =================================================
                   WIF3006 CBSE OSGi Application Stopped
                =================================================""");
    }
}
