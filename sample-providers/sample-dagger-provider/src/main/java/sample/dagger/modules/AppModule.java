package sample.dagger.modules;

import java.util.Map;
import java.util.concurrent.Executors;

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
            new CommandStackDispatcher(commandHandlerProvider), 
            Executors.newWorkStealingPool());
    }


    // Inject provider to avoid cyclic dependency between CommandDispatcher and CommandHandlers
    @Provides
    public CommandHandlerProvider commandHandlerProvider(
            Provider<Map<Class<?>, CommandHandler<?>>> commandHandlerMapProvider) {
        return new DaggerCommandHandlerProvider(commandHandlerMapProvider);
    }
}
