module sample.dagger {
    requires io.github.xerprojects.xerj.commandstack;
    requires java.logging;

    // Dagger required modules...
    requires dagger;
    requires javax.inject;
    requires java.compiler;

    exports sample.dagger;
}
