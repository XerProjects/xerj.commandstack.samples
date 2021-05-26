package sample.dagger;

import javax.inject.Inject;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import sample.dagger.commands.PingCommand;
import sample.dagger.commands.PongCommand;
import sample.dagger.components.DaggerAppComponent;

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

    public static void main( String[] args ) {
        App app = DaggerAppComponent.create().app();
        app.run();
    }
}
