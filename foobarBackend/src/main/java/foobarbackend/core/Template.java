package foobarbackend.core;

import java.util.Optional;

import foobarbackend.api.Foobar;

import static java.lang.String.format;

public class Template {
    private final String content;
    private final Foobar defaultFoobar;

    public Template(String content, Foobar defaultFoobar) {
        this.content = content;
        this.defaultFoobar = defaultFoobar;
    }

    public String render(Optional<Foobar> foobar) {
        return format(content, foobar.orElse(defaultFoobar));
    }
}