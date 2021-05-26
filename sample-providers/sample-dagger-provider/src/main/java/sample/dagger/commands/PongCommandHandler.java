package sample.dagger.commands;

import java.util.logging.Logger;

import javax.inject.Inject;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandler;

public class PongCommandHandler implements CommandHandler<PongCommand> {

	private static final Logger LOGGER = Logger.getLogger(PongCommandHandler.class.getName());

    private final CommandDispatcher commandDispatcher;

    @Inject
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
