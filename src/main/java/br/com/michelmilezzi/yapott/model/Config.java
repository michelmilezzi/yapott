package br.com.michelmilezzi.yapott.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    
    public abstract String getFormatedSetting();

    public abstract void resolveSetting();

}
