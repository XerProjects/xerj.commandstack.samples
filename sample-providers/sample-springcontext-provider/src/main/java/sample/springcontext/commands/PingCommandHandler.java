package sample.springcontext.commands;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

@Component
public class PingCommandHandler implements CommandHandler<PingCommand> {

    private static final Logger LOGGER = Logger.getLogger(PingCommandHandler.class.getName());

	@Override
	public void handle(PingCommand command) {
		LOGGER.info("PING!");
	}
    
}
