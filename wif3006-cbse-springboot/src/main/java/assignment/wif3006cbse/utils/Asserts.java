package assignment.wif3006cbse.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;

import java.util.Collection;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Asserts {

    @Contract("false, _ -> fail")
    public static void state(boolean state, String message) {
        isTrue(state, () -> new IllegalStateException(message));
    }

    @Contract("null, _ -> fail")
    public static <X extends RuntimeException> void notEmpty(Collection<?> collection,
                                                             Supplier<X> exception) {
        isTrue(collection != null && !collection.isEmpty(), exception);
    }

    @Contract("null, _ -> fail")
    public static void notEmpty(Collection<?> collection, String message) {
        notEmpty(collection, () -> new IllegalArgumentException(message));
    }

    @Contract("null, _ -> fail")
    public static void notNull(Object obj, String message) {
        isTrue(obj != null, () -> new IllegalStateException(message));
    }

    @Contract("null, _ -> fail")
    public static void notBlank(String text, String message) {
        isTrue(Validators.isNotBlank(text), () -> new IllegalArgumentException(message));
    }

    @Contract("true, _ -> fail")
    public static void isFalse(boolean condition, Supplier<RuntimeException> exception) {
        isTrue(!condition, exception);
    }

    @Contract("false, _ -> fail")
    public static <X extends RuntimeException> void isTrue(boolean condition,
                                                           Supplier<X> exception) {
        if (!condition) {
            throw exception.get();
        }
    }
}
