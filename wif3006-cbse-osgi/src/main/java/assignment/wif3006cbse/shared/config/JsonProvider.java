package assignment.wif3006cbse.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.ext.Provider;

@Provider
@Component(service = {
    javax.ws.rs.ext.MessageBodyReader.class,
    javax.ws.rs.ext.MessageBodyWriter.class
}, property = {
    "osgi.jaxrs.extension=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=community)"
})
public class JsonProvider extends JacksonJsonProvider {

    public JsonProvider() {
        super(new com.fasterxml.jackson.databind.ObjectMapper()
            .registerModule(new com.fasterxml.jackson.module.paramnames.ParameterNamesModule())
            .registerModule(new com.fasterxml.jackson.datatype.jdk8.Jdk8Module())
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .disable(
                com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
    }
}
