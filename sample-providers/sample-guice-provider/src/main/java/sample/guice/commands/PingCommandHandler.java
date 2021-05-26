package sample.guice.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

public class PingCommandHandler implements CommandHandler<PingCommand> {

    private final Logger logger;

    @Inject
	public PingCommandHandler(Logger logger) {
		this.logger = logger;
    }

    @Override
    public void handle(PingCommand command) {
        logger.log(Level.INFO, "PING!");
    }
    
}
