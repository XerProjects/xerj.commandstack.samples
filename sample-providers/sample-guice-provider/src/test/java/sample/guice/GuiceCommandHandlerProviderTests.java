package sample.guice;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import io.github.xerprojects.xerj.commandstack.CommandHandler;

import sample.guice.entities.AppModule;
import sample.guice.entities.NullModule;
import sample.guice.entities.TestCommand;
import sample.guice.entities.TestCommandHandler;

public class GuiceCommandHandlerProviderTests {
	@Nested
	public class Constructor {
		@Test
		@DisplayName("should throw when injector argument is null")
		public void test1() {
			assertThrows(IllegalArgumentException.class, () -> {
				new GuiceCommandHandlerProvider(null);
			});
		}
	}
			
	@Nested
	public class GetCommandHandlerForMethod {

		@Test
		@DisplayName("should provide command handler from injector")
		public void test1() {
			Injector injector = Guice.createInjector(new AppModule());
			var provider = new GuiceCommandHandlerProvider(injector);
			Optional<CommandHandler<TestCommand>> resolvedHandler = provider.getCommandHandlerFor(TestCommand.class);
			
			CommandHandler<TestCommand> providerInstance = resolvedHandler.get();
			
			assertNotNull(providerInstance);
			assertTrue(providerInstance instanceof TestCommandHandler);
		}
		
		@Test
		@DisplayName("should throw when command argument is null")
		public void test2() {
			assertThrows(IllegalArgumentException.class, () -> {
				Injector injector = Guice.createInjector(new AppModule());
				var provider = new GuiceCommandHandlerProvider(injector);
				provider.getCommandHandlerFor(null);
			});
		}

		@Test
		@DisplayName("should return empty optional if no command handler is found")
		public void test3() {
			Injector injector = Guice.createInjector(new NullModule());
			var provider = new GuiceCommandHandlerProvider(injector);
			Optional<CommandHandler<TestCommand>> handler = 
				provider.getCommandHandlerFor(TestCommand.class);

			assertTrue(handler.isEmpty());
		}
	}
}