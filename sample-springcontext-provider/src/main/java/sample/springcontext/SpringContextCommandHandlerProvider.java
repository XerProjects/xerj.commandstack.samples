package sample.springcontext;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

import io.github.xerprojects.xerj.commandstack.CommandHandler;
import io.github.xerprojects.xerj.commandstack.CommandHandlerProvider;

/**
 * 
 * @author Joel Jeremy Marquez
 */
public class SpringContextCommandHandlerProvider implements CommandHandlerProvider {

    private final ApplicationContext appContext;

    /**
     * Constructor.
     * @param appContext Spring's application context.
     */
    public SpringContextCommandHandlerProvider(ApplicationContext appContext) {

        if (appContext == null) {
            throw new IllegalArgumentException("Spring application context type must not be null.");
        }

        this.appContext = appContext;
    }

    /**
	 * Get command handler for the given command type. If multiple command handlers are found for
     * the given command type, a {@link SpringDuplicateCommandHandlerFoundException} will be thrown.
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

        String[] handlerBeanNames = appContext.getBeanNamesForType(
            ResolvableType.forClassWithGenerics(CommandHandler.class, commandType));

        if (handlerBeanNames.length == 0) {
            return Optional.empty();
        }

        if (handlerBeanNames.length > 1) {
            throw new SpringDuplicateCommandHandlerFoundException(commandType, 
                "Multiple command handlers that handle " + commandType
                + " have been detected by Spring Application Context.");
        }

        @SuppressWarnings("unchecked")
        CommandHandler<TCommand> instance = appContext.getBean(handlerBeanNames[0], CommandHandler.class);
        return Optional.ofNullable(instance);
    }
}