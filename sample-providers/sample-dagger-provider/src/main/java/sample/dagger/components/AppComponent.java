package sample.dagger.components;

import dagger.Component;
import sample.dagger.App;
import sample.dagger.modules.AppModule;
import sample.dagger.modules.CommandHandlersModule;

@Component(modules = {
    AppModule.class,
    CommandHandlersModule.class
})
public interface AppComponent {
    App app();
}
