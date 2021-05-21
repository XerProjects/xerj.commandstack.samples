module sample.springcontext {
    requires transitive spring.context;
    requires io.github.xerprojects.xerj.commandstack;
    requires java.logging;
    requires spring.core;
    requires spring.beans;
    exports sample.springcontext;
}