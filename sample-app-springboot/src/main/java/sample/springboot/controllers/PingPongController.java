package sample.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import sample.springboot.commands.PingCommand;
import sample.springboot.commands.PongCommand;
import sample.springboot.commands.UnhandledCommand;

@RestController
public class PingPongController {

    private final CommandDispatcher commandDispatcher;

	public PingPongController(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
    }

    @GetMapping("/ping")
    public String ping() {
        commandDispatcher.send(new PingCommand());
        return "PING!";
    }

    @GetMapping("/pong")
    public String pong() {
        commandDispatcher.send(new PongCommand());
        return "PONG!";
    }

    @GetMapping("/unhandled")
    public String noMapping() {
        commandDispatcher.send(
            new UnhandledCommand(1, "This is a command that has no registered handlers."));
        return "UNHANDLED!";
    }

}