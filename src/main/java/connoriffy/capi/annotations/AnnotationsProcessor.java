package connoriffy.capi.annotations;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class AnnotationsProcessor {
    public static void init() {
    }

    public static void onShutdown() {
        Set<Method> onShutdown = new Reflections(new MethodAnnotationsScanner()).getMethodsAnnotatedWith(OnShutdown.class);
        onShutdown.forEach(method -> {
            try {
                method.invoke(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
