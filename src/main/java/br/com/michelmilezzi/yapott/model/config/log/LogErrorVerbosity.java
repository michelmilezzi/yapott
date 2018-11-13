package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogErrorVerbosity extends RawConfig {

    public LogErrorVerbosity(Environment env) {
        super("log_error_verbosity", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("default");
    }

}
