package sample.springcontext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.xerprojects.xerj.commandstack.CommandHandler;
import sample.springcontext.entities.TestCommand;
import sample.springcontext.entities.TestCommandHandler;
import sample.springcontext.entities.springconfigs.BeanConfig;
import sample.springcontext.entities.springconfigs.DuplicateHandlerConfig;
import sample.springcontext.entities.springconfigs.NullConfig;

public class SpringContextCommandHandlerProviderTests {
	@Nested
	public class Constructor {
		@Test
		@DisplayName("should throw when applicationContext argument is null")
		public void test1() {
			assertThrows(IllegalArgumentException.class, () -> {
				new SpringContextCommandHandlerProvider(null);
			});
		}
	}
			
	@Nested
	public class GetCommandHandlerForMethod {
		@Test
		@DisplayName("should provide command handler from application context")
		public void test1() {
			try (var appContext = new AnnotationConfigApplicationContext(BeanConfig.class)) {
				var provider = new SpringContextCommandHandlerProvider(appContext);
				Optional<CommandHandler<TestCommand>> resolvedHandler = 
					provider.getCommandHandlerFor(TestCommand.class);
				
				CommandHandler<TestCommand> instance = resolvedHandler.get();
				
				assertNotNull(instance);
				assertTrue(instance instanceof TestCommandHandler);
			}
		}
		
		@Test
		@DisplayName("should throw when command argument is null")
		public void test2() {
			assertThrows(IllegalArgumentException.class, () -> {
				try (var appContext = new AnnotationConfigApplicationContext(BeanConfig.class)) {
					var provider = new SpringContextCommandHandlerProvider(appContext);
					provider.getCommandHandlerFor(null);
				}
			});
		}
		
		@Test
		@DisplayName("should throw when duplicate command handler is found")
		public void test3() {
			assertThrows(SpringDuplicateCommandHandlerFoundException.class, () -> {
				try (var appContext = new AnnotationConfigApplicationContext(DuplicateHandlerConfig.class)) {
					var provider = new SpringContextCommandHandlerProvider(appContext);
					provider.getCommandHandlerFor(TestCommand.class);
				}
			});
		}

		@Test
		@DisplayName("should return empty optional if no command handler is found.")
		public void test4() {
			try (var appContext = new AnnotationConfigApplicationContext(NullConfig.class)) {
				var provider = new SpringContextCommandHandlerProvider(appContext);
				Optional<CommandHandler<TestCommand>> resolvedHandler = 
					provider.getCommandHandlerFor(TestCommand.class);
							
				assertNotNull(resolvedHandler);
				assertTrue(!resolvedHandler.isPresent());
			}
		}
	}
}