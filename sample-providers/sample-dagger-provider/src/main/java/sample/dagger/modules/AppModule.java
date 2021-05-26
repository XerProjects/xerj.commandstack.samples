package sample.dagger.modules;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;
import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandler;
import io.github.xerprojects.xerj.commandstack.CommandHandlerProvider;
import io.github.xerprojects.xerj.commandstack.dispatchers.CommandStackDispatcher;
import io.github.xerprojects.xerj.commandstack.dispatchers.async.AsyncCommandDispatcher;
import sample.dagger.DaggerCommandHandlerProvider;

@Module
public class AppModule {

    @Provides
    public CommandDispatcher commandDispatcher(CommandHandlerProvider commandHandlerProvider) {
        return new AsyncCommandDispatcher(
            new CommandStackDispatcher(commandHandlerProvider, AppModule::logUnhandledCommand), 
            Executors.newWorkStealingPool());
    }

    // Inject provider to avoid cyclic dependency between CommandDispatcher and CommandHandlers
    @Provides
    public CommandHandlerProvider commandHandlerProvider(
            Provider<Map<Class<?>, CommandHandler<?>>> commandHandlerMapProvider) {
        return new DaggerCommandHandlerProvider(commandHandlerMapProvider);
    }

    private static final Logger DISPATCHER_LOGGER = 
        Logger.getLogger(CommandStackDispatcher.class.getName());

    private static <TCommand> void logUnhandledCommand(TCommand command) {
        DISPATCHER_LOGGER.log(Level.WARNING, 
            "No command handler found for command of type {0}.", 
            command.getClass());
    }
}
