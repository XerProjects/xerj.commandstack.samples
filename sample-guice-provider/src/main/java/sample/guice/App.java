package sample.guice;

import com.google.inject.Guice;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import sample.guice.commands.PingCommand;
import sample.guice.commands.PongCommand;
import sample.guice.modules.AppModule;

public class App {
    public static void main(String[] args) {
        var injector = Guice.createInjector(new AppModule());

        CommandDispatcher commandDispatcher = injector.getInstance(CommandDispatcher.class);

        commandDispatcher.send(new PingCommand());
        commandDispatcher.send(new PongCommand());
    }
}
