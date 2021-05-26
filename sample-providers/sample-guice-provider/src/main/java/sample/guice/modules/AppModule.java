package sample.guice.modules;

import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandler;
import io.github.xerprojects.xerj.commandstack.CommandHandlerProvider;
import io.github.xerprojects.xerj.commandstack.dispatchers.CommandStackDispatcher;
import io.github.xerprojects.xerj.commandstack.dispatchers.async.AsyncCommandDispatcher;
import sample.guice.GuiceCommandHandlerProvider;
import sample.guice.commands.PingCommand;
import sample.guice.commands.PingCommandHandler;
import sample.guice.commands.PongCommand;
import sample.guice.commands.PongCommandHandler;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<CommandHandler<PingCommand>>(){}).to(PingCommandHandler.class);
        bind(new TypeLiteral<CommandHandler<PongCommand>>(){}).to(PongCommandHandler.class);
    }

    @Provides
    public CommandDispatcher commandDispatcher(CommandHandlerProvider commandHandlerProvider) {
        // Decorated with async command dispatcher.

        // If async command dispatch is not needed, you can just:
        // return new CommandStackDispatcher(commandHandlerProvider, AppModule::logUnhandledCommand);
        
        return new AsyncCommandDispatcher(
            new CommandStackDispatcher(commandHandlerProvider, AppModule::logUnhandledCommand),
            Executors.newWorkStealingPool());
    }

    @Provides
    public CommandHandlerProvider commandHandlerProvider(Injector injector) {
        return new GuiceCommandHandlerProvider(injector);
    }

    private static final Logger DISPATCHER_LOGGER = 
        Logger.getLogger(CommandStackDispatcher.class.getName());

    private static <TCommand> void logUnhandledCommand(TCommand command) {
        DISPATCHER_LOGGER.log(Level.WARNING, 
            "No command handler found for command of type {0}.", 
            command.getClass());
    }
}
