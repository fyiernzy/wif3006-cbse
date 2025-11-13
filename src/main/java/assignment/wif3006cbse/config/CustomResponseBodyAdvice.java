package assignment.wif3006cbse.config;

import assignment.wif3006cbse.utils.Validators;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> type = returnType.getParameterType();
        return !ResponseEntity.class.isAssignableFrom(type);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (Validators.isNull(body)) {
            return ResponseEntity.ok();
        }

        if (response instanceof ServletServerHttpResponse servletResponse) {
            int rawStatus = servletResponse.getServletResponse().getStatus();
            HttpStatus status = HttpStatus.resolve(rawStatus);
            return ResponseEntity
                .status(Validators.isNotNull(status) ? status : HttpStatus.OK)
                .body(body);
        }

        return ResponseEntity.ok(body);
    }
}