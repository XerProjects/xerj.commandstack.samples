package sample.springcontext.entities.springconfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.xerprojects.xerj.commandstack.CommandHandler;
import sample.springcontext.entities.TestCommand;
import sample.springcontext.entities.TestCommandHandler;

@Configuration
public class DuplicateHandlerConfig {
    @Bean
    public CommandHandler<TestCommand> getTestCommandHandler1() {
        return new TestCommandHandler();  
    }

    @Bean
    public CommandHandler<TestCommand> getTestCommandHandler2() {
        return new TestCommandHandler();  
    }
}