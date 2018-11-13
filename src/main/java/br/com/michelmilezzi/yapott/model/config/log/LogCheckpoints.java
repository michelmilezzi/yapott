package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogCheckpoints extends RawConfig {

    public LogCheckpoints(Environment env) {
        super("log_checkpoints", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("on");
    }

}
