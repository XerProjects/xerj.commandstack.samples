package sample.dagger.modules;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import io.github.xerprojects.xerj.commandstack.CommandDispatcher;
import io.github.xerprojects.xerj.commandstack.CommandHandler;
import sample.dagger.commands.PingCommand;
import sample.dagger.commands.PingCommandHandler;
import sample.dagger.commands.PongCommand;
import sample.dagger.commands.PongCommandHandler;

@Module
public class CommandHandlersModule {
    @Provides
    @IntoMap
    @ClassKey(PingCommand.class)
    public CommandHandler<?> pingCommandHandler() {
        return new PingCommandHandler();
    }

    @Provides
    @IntoMap
    @ClassKey(PongCommand.class)
    public CommandHandler<?> pongCommandHandler(CommandDispatcher commandDispatcher) {
        return new PongCommandHandler(commandDispatcher);
    }
}
