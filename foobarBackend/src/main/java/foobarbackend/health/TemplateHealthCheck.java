package foobarbackend.health;

import com.codahale.metrics.health.HealthCheck;

import java.util.Optional;

import foobarbackend.core.*;

public class TemplateHealthCheck extends HealthCheck {
    private final Template template;

    public TemplateHealthCheck(Template template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        template.render(Optional.empty());
        return Result.healthy();
    }
}