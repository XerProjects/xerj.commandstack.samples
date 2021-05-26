package sample.guice;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.util.Types;

import io.github.xerprojects.xerj.commandstack.CommandHandler;
import io.github.xerprojects.xerj.commandstack.CommandHandlerProvider;

/**
 * Command handler provider that gets its command handler instances from an {@link Injector}.
 * 
 * @author Joel Jeremy Marquez
 */
public class GuiceCommandHandlerProvider implements CommandHandlerProvider {

    private static final Logger LOGGER = Logger.getLogger(GuiceCommandHandlerProvider.class.getName());

    private final Injector injector;

    /**
     * Constructor.
     * @param injector Guice's injector instance.
     */
    public GuiceCommandHandlerProvider(Injector injector) {

        if (injector == null) {
            throw new IllegalArgumentException("Guice injector must not be null.");
        }
        
        this.injector = injector;
    }

    /**
	 * Get command handler for the given command type.
	 * @param <TCommand> The command type.
	 * @param commandType The command type.
	 * @return The command handler instance that is registered for the command type.
	 * If there is no command handler was registered, an empty Optional will be returned.
	 */
    @Override
	public <TCommand> Optional<CommandHandler<TCommand>> getCommandHandlerFor(
        Class<TCommand> commandType) {

        if (commandType == null) {
            throw new IllegalArgumentException("Command type must not be null.");
        }
        
        try {
            @SuppressWarnings("unchecked")
            CommandHandler<TCommand> instance = (CommandHandler<TCommand>)injector.getInstance(
                Key.get(Types.newParameterizedType(CommandHandler.class, commandType)));

            return Optional.ofNullable(instance);
        } catch (ConfigurationException ex) {
            LOGGER.log(Level.WARNING, "Exception occurred while retrieving command handler.", ex);
            return Optional.empty();
        }
    }
}