package sample.guice.commands;

import java.util.logging.Logger;

import javax.inject.Inject;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandler;

public class PongCommandHandler implements CommandHandler<PongCommand> {

    private final CommandDispatcher commandDispatcher;
	private final Logger logger;

    @Inject
	public PongCommandHandler(CommandDispatcher commandDispatcher, Logger logger) {
		this.commandDispatcher = commandDispatcher;
		this.logger = logger;
    }

	@Override
	public void handle(PongCommand command) {
		logger.info("PONG!");

        logger.info("Sending PING command from PongCommandHandler...");
        commandDispatcher.send(new PingCommand());
	}
    
}
