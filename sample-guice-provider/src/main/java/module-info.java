module sample.guice {
    requires io.github.xerprojects.xerj.commandstack;
    requires java.logging;

    // Guice required modules...
    requires com.google.guice;
    requires com.google.common;
    requires javax.inject;
    requires failureaccess;
    requires aopalliance;

    exports sample.guice.commands;
    exports sample.guice.modules;
}