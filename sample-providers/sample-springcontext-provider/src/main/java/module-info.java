module sample.springcontext {
    requires io.github.xerprojects.xerj.commandstack;
    requires java.logging;

    // Spring required modules...
    requires spring.aop;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.expression;
    requires org.apache.commons.logging;
    requires org.slf4j;

    // Spring required exports...
    exports sample.springcontext.config to spring.beans, spring.context;

    // Spring required opens...
    opens sample.springcontext to spring.beans, spring.context, spring.core;
    opens sample.springcontext.commands to spring.beans;
    opens sample.springcontext.config to spring.core;
}