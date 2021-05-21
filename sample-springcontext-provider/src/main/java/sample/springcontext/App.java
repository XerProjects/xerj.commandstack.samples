package sample.springcontext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import sample.springcontext.commands.PingCommand;
import sample.springcontext.commands.PongCommand;

@ScanCommandHandlers
@Configuration
public class App {
    public static void main(String[] args) {
        try (var appContext = new AnnotationConfigApplicationContext(App.class)) {
            CommandDispatcher commandDispatcher = appContext.getBean(CommandDispatcher.class);

            commandDispatcher.send(new PingCommand());
            commandDispatcher.send(new PongCommand());
        }
    }
}
