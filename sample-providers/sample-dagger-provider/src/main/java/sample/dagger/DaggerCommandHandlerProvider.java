package sample.dagger;

import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import io.github.xerprojects.xerj.commandstack.CommandHandler;
import io.github.xerprojects.xerj.commandstack.CommandHandlerProvider;

public class DaggerCommandHandlerProvider implements CommandHandlerProvider {

    private final Provider<Map<Class<?>, CommandHandler<?>>> commandHandlerMapProvider;

    // Inject provider to avoid cyclic dependency between CommandDispatcher and CommandHandlers
	@Inject
    public DaggerCommandHandlerProvider(
            Provider<Map<Class<?>, CommandHandler<?>>> commandHandlerMapProvider) {
		this.commandHandlerMapProvider = commandHandlerMapProvider;
    }

	@Override
	public <TCommand> Optional<CommandHandler<TCommand>> getCommandHandlerFor(
            Class<TCommand> commandType) {

        Map<Class<?>, CommandHandler<?>> commandHandlerMap = commandHandlerMapProvider.get();

		CommandHandler<?> handler = commandHandlerMap.get(commandType);

        if (handler == null) {
            return Optional.empty();
        }

        @SuppressWarnings("unchecked")
        CommandHandler<TCommand> resovledCommand = (CommandHandler<TCommand>)handler;
        
        return Optional.of(resovledCommand);
	}

}
