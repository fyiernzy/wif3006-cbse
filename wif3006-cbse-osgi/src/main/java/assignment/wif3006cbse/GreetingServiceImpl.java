
package assignment.wif3006cbse;

import org.osgi.service.component.annotations.Component;

@Component(service = GreetingService.class)
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String name) {
        String effectiveName = (name == null || name.isBlank()) ? "world" : name;
        return "Hello, " + effectiveName + "!";
    }
}
