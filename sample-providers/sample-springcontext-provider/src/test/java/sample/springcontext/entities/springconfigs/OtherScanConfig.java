package sample.springcontext.entities.springconfigs;

import org.springframework.context.annotation.Configuration;

import sample.springcontext.ScanCommandHandlers;
import sample.springcontext.entities.other.OtherTestCommandHandler;

@Configuration
@ScanCommandHandlers(OtherTestCommandHandler.class)
public class OtherScanConfig {
    
}
