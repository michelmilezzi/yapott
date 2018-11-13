package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogRotationAge extends RawConfig {

    public LogRotationAge(Environment env) {
        super("log_rotation_age", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("1d");
    }

}
