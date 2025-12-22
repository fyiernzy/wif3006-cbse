
package assignment.wif3006cbse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component
public class DemoComponent {

    @Activate
    void activate() {
        System.out.println("✅ DemoComponent activated");
    }
}
