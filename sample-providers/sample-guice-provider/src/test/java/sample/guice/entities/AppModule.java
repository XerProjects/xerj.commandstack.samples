package sample.guice.entities;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

public class AppModule extends AbstractModule {
    @Override 
    protected void configure() {
      bind(new TypeLiteral<CommandHandler<TestCommand>>(){})
        .to(TestCommandHandler.class);
    }
}