package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogMinErrorStatement extends RawConfig {

    public LogMinErrorStatement(Environment env) {
        super("log_min_error_statement", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("warning");
    }

}
