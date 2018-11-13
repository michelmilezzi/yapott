package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class Port extends RawConfig {

    public Port(Environment env) {
        super("port", env);
    }
    
    @Override
    public void resolveSetting() {
        setSetting(String.valueOf(getEnv().getPort()));
    }

}
