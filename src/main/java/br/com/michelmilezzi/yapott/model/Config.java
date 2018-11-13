package br.com.michelmilezzi.yapott.model;

public abstract class Config {

    private String name;
    private String setting;
    private Environment env;
    
    public Config(String name, final Environment env) {
        super();
        this.name = name;
        this.env = env;
        resolveSetting();
    }

    public String getName() {
        return name;
    }
    
    public String getSetting() {
        return setting;
    }
    
    public Environment getEnv() {
        return env;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }
    
    public abstract String getFormatedSetting();

    public abstract void resolveSetting();

}
