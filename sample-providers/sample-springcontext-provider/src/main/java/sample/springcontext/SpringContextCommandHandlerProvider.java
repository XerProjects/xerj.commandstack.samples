package sample.springcontext;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

import io.github.xerprojects.xerj.commandstack.CommandHandler;
import io.github.xerprojects.xerj.commandstack.CommandHandlerProvider;

/**
 * Command handler provider implementation that gets its command handler instances
 * from Spring's {@link ApplicationContext}.
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
            throw new IllegalArgumentException("Spring application context must not be null.");
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
                "Multiple command handlers that handle " + commandType + 
                " have been detected by Spring Application Context.");
        }

        @SuppressWarnings("unchecked")
        CommandHandler<TCommand> instance = appContext.getBean(handlerBeanNames[0], CommandHandler.class);
        return Optional.ofNullable(instance);
    }

    /**
     * This is usually thrown if there are multiple command handlers registered for a given command type.
     * 
     * @author Joel Jeremy Marquez
     */
    public static class SpringDuplicateCommandHandlerFoundException extends RuntimeException {

        private static final long serialVersionUID = 1856796726215848487L;
        private static final String DEFAULT_EXCEPTION_MESSAGE = "A duplicate command handler was registered for %s.";
        
        private final Class<?> commandType;
        
        public SpringDuplicateCommandHandlerFoundException(Class<?> commandType, String message, Throwable cause) {
            super(message, cause);
            this.commandType = commandType;
        }
        
        public SpringDuplicateCommandHandlerFoundException(Class<?> commandType, String message) {
            this(commandType, buildExceptionMessage(commandType, message), null);
        }
        
        public SpringDuplicateCommandHandlerFoundException(Class<?> commandType) {
            this(commandType, null);
        }

        public Class<?> getCommandType() {
            return commandType;
        }
        
        private static final String buildExceptionMessage(Class<?> commandType, String message) {

            if (message == null || message.isBlank()) {
                message = String.format(DEFAULT_EXCEPTION_MESSAGE, commandType);
            }
            
            return message;
        }
    }
}