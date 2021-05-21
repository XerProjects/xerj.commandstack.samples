package sample.guice.entities;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;

public class AppModule extends AbstractModule {
    @Override 
    protected void configure() {
      bind(new TypeLiteral<CommandHandler<TestCommand>>(){})
        .to(TestCommandHandler.class);
    }
}