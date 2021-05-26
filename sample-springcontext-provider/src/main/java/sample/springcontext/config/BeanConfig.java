package sample.springcontext.config;

import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandlerProvider;
import io.github.xerprojects.xerj.commandstack.dispatchers.CommandStackDispatcher;
import io.github.xerprojects.xerj.commandstack.dispatchers.async.AsyncCommandDispatcher;
import sample.springcontext.App;
import sample.springcontext.ScanCommandHandlers;
import sample.springcontext.SpringContextCommandHandlerProvider;

@ScanCommandHandlers(App.class)
@Configuration
public class BeanConfig {
    @Bean
    public CommandDispatcher commandDispatcher(CommandHandlerProvider commandHandlerProvider) {
        return new AsyncCommandDispatcher(
            new CommandStackDispatcher(commandHandlerProvider),
            Executors.newWorkStealingPool()
        );
    }

    @Bean
    public CommandHandlerProvider commandHandlerProvider(ApplicationContext appContext) {
        return new SpringContextCommandHandlerProvider(appContext);
    }
}
