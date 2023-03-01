package com.mario.annoation;
import com.mario.scan.BoomComponentScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(BoomComponentScanRegistrar.class)
public @interface EnableBoom {


}
