package sample.guice;

import javax.inject.Inject;

import com.google.inject.Guice;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import sample.guice.commands.PingCommand;
import sample.guice.commands.PongCommand;
import sample.guice.modules.AppModule;

public class App {

    private final CommandDispatcher commandDispatcher;

    @Inject
	public App(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
    }

    public void run() {
        commandDispatcher.send(new PingCommand());
        commandDispatcher.send(new PongCommand());
    }

    public static void main(String[] args) {
        var injector = Guice.createInjector(new AppModule());

        App app = injector.getInstance(App.class);
        app.run();
    }
}
