package sample.springcontext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import sample.springcontext.commands.PingCommand;
import sample.springcontext.commands.PongCommand;
import sample.springcontext.config.BeanConfig;

public class App {
    public static void main(String[] args) throws InterruptedException {
        try (var appContext = new AnnotationConfigApplicationContext(BeanConfig.class)) {
            CommandDispatcher commandDispatcher = appContext.getBean(CommandDispatcher.class);

            commandDispatcher.send(new PingCommand());
            commandDispatcher.send(new PongCommand());
        }
    }
}
