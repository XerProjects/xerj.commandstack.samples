package sample.springcontext;

/**
 * This is usually thrown if there are multiple command handlers registered for a given command type.
 * 
 * @author Joel Jeremy Marquez
 */
public class SpringDuplicateCommandHandlerFoundException extends RuntimeException {

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
