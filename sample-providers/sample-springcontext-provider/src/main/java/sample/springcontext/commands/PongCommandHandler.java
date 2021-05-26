package sample.springcontext.commands;

import java.util.logging.Logger;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandler;

public class PongCommandHandler implements CommandHandler<PongCommand> {

	private static final Logger LOGGER = Logger.getLogger(PongCommandHandler.class.getName());

    private CommandDispatcher commandDispatcher;

	public PongCommandHandler(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
    }

	@Override
	public void handle(PongCommand command) {
        LOGGER.info("PONG!");

        LOGGER.info("Sending PING command from PongCommandHandler...");
        commandDispatcher.send(new PingCommand());
	}
    
}
