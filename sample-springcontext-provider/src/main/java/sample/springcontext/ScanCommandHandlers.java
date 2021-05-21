package sample.springcontext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

/**
 * Instructs Spring to scan for command handlers based on the base package classes.
 * 
 * @author Joel Jeremy Marquez
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan(
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, 
        value = CommandHandler.class),
    useDefaultFilters = false)
public @interface ScanCommandHandlers {
    @AliasFor("basePackageClasses")
    Class<?>[] value() default {};

    @AliasFor("value")
    Class<?>[] basePackageClasses() default {};
}