package sample.springcontext.config;

import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandlerProvider;
import io.github.xerprojects.xerj.commandstack.dispatchers.CommandStackDispatcher;
import io.github.xerprojects.xerj.commandstack.dispatchers.async.AsyncCommandDispatcher;
import sample.springcontext.App;
import sample.springcontext.SpringContextCommandHandlerProvider;

// One can use @ScanCommandHandlers if command handlers are in a separate library.
// @ScanCommandHandlers(App.class)
@ComponentScan(basePackageClasses = App.class)
@Configuration
public class BeanConfig {
    
    @Bean
    public CommandDispatcher commandDispatcher(CommandHandlerProvider commandHandlerProvider) {

        // If async command dispatch is not needed, you can just:
        // return new CommandStackDispatcher(commandHandlerProvider, BeanConfig::logUnhandledCommand);

        return new AsyncCommandDispatcher(
            new CommandStackDispatcher(commandHandlerProvider, BeanConfig::logUnhandledCommand),
            Executors.newWorkStealingPool()
        );
    }

    @Bean
    public CommandHandlerProvider commandHandlerProvider(ApplicationContext appContext) {
        return new SpringContextCommandHandlerProvider(appContext);
    }

    private static final Logger DISPATCHER_LOGGER = 
        Logger.getLogger(CommandStackDispatcher.class.getName());

    private static <TCommand> void logUnhandledCommand(TCommand command) {
        DISPATCHER_LOGGER.log(Level.WARNING, 
            "No command handler found for command of type {0}.", 
            command.getClass());
    }
}
