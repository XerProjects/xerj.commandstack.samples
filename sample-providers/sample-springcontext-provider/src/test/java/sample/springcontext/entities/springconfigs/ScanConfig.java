package sample.springcontext.entities.springconfigs;

import org.springframework.context.annotation.Configuration;

import sample.springcontext.ScanCommandHandlers;
import sample.springcontext.entities.BaseCommandHandler;

@Configuration
@ScanCommandHandlers(BaseCommandHandler.class)
public class ScanConfig {
    
}