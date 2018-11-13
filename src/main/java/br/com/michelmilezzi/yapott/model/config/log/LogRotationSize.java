package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogRotationSize extends RawConfig {

    public LogRotationSize(Environment env) {
        super("log_rotation_size", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("64MB");
    }

}
