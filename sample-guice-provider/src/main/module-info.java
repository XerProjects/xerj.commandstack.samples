module sample.guice {
    requires io.github.xerprojects.xerj.commandstack;
    requires com.google.guice;
    exports sample.guice;
    exports sample.guice.commands;
    exports sample.guice.modules;
}