package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogStatement extends RawConfig {

    public LogStatement(Environment env) {
        super("log_statement", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("'ddl'");
    }

}
