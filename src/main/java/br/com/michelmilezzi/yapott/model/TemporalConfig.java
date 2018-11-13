package br.com.michelmilezzi.yapott.model;

import java.text.MessageFormat;

public abstract class TemporalConfig extends Config {

    public TemporalConfig(String name, Environment env) {
        super(name, env);
    }

    @Override
    public String getFormatedSetting() {
        return MessageFormat.format("{0}min", getSetting());
    }

    public abstract void resolveSetting();

}
