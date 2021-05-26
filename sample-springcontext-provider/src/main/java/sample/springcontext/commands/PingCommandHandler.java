package sample.springcontext.commands;

import java.util.logging.Logger;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

public class PingCommandHandler implements CommandHandler<PingCommand> {

    private static final Logger LOGGER = Logger.getLogger(PingCommandHandler.class.getName());

	@Override
	public void handle(PingCommand command) {
		LOGGER.info("PING!");
	}
    
}
