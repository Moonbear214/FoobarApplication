package foobarbackend;

import io.dropwizard.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import foobarbackend.api.*;
import foobarbackend.core.*;

public class FooBarConfiguration extends Configuration {
    private String template;
    private Foobar defaultFoobar = new Foobar();

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public Foobar getDefaultFoobar() {
        return defaultFoobar;
    }

    @JsonProperty
    public void setDefaultFoobar(Foobar foobar) {
        this.defaultFoobar = foobar;
    }

    public Template buildTemplate() {
        return new Template(template, defaultFoobar);
    }
}