package sample.springboot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandler;

public class PongCommandHandler implements CommandHandler<PongCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PongCommandHandler.class);
	private final CommandDispatcher commandDispatcher;

	public PongCommandHandler(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
    }

	@Override
	public void handle(PongCommand command) {
		LOGGER.info("PONG!");
        
        LOGGER.info("Dispatching PING command from PongCommandHandler...");
        commandDispatcher.send(new PingCommand());
	}
    
}
