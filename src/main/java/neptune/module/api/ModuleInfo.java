package neptune.module.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String name() default "";
    String description() default "";
    int key() default 0;
    Flags[] flags() default {};
}