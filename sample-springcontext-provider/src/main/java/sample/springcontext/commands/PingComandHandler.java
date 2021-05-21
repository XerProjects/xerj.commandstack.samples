package sample.springcontext.commands;

import java.util.logging.Logger;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

public class PingComandHandler implements CommandHandler<PingCommand> {

    private final Logger logger;

	public PingComandHandler(Logger logger) {
		this.logger = logger;
    }

	@Override
	public void handle(PingCommand command) {
		logger.info("PING!");
	}
    
}
