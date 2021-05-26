package sample.springcontext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import sample.springcontext.commands.PingCommand;
import sample.springcontext.commands.PongCommand;
import sample.springcontext.config.BeanConfig;

@Component
public class App {

    private final CommandDispatcher commandDispatcher;

	public App(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
    }

    public void run() {
        commandDispatcher.send(new PingCommand());
        commandDispatcher.send(new PongCommand());
    }

    public static void main(String[] args) throws InterruptedException {
        try (var appContext = new AnnotationConfigApplicationContext(BeanConfig.class)) {
            App app = appContext.getBean(App.class);
            app.run();
        }
    }
}
