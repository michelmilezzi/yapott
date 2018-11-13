package br.com.michelmilezzi.yapott.model;

public abstract class RawConfig extends Config {

    public RawConfig(String name, Environment env) {
        super(name, env);
    }

    @Override
    public String getFormatedSetting() {
        return getSetting();
    }

}
