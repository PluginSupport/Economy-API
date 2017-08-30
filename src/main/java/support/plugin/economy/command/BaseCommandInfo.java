package support.plugin.economy.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by eric on 30/08/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BaseCommandInfo {

    String description();

    String usage();

    String permission();

    String[] aliases();

    boolean op(); // If true, only an operator can run this command.
}
