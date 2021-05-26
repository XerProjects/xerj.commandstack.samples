package sample.springboot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

@Component
public class PingCommandHandler implements CommandHandler<PingCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingCommandHandler.class);

	@Override
	public void handle(PingCommand command) {
		LOGGER.info("PING!");
	}
    
}
