package assignment.wif3006cbse.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Validators {

    public static boolean isTrue(boolean condition) {
        return condition;
    }

    public static boolean isFalse(boolean condition) {
        return !condition;
    }

    public static boolean isNotNull(Object object) {
        return Objects.nonNull(object);
    }

    public static boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    public static boolean isEqual(Object a, Object b) {
        return Objects.equals(a, b);
    }

    public static boolean isNotEqual(Object a, Object b) {
        return !Objects.equals(a, b);
    }

    public static boolean isNotBlank(String str) {
        return StringUtils.hasText(str);
    }

    public static boolean isBlank(String str) {
        return !StringUtils.hasText(str);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }
}
