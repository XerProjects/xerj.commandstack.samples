module sample.springboot {
    requires io.github.xerprojects.xerj.commandstack;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    // Spring required modules...
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.web;
    requires org.slf4j;
}
