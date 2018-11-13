package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogDuration extends RawConfig {

    public LogDuration(Environment env) {
        super("log_duration", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("off");
    }

}
