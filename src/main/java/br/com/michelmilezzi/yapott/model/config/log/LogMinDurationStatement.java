package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogMinDurationStatement extends RawConfig {

    public LogMinDurationStatement(Environment env) {
        super("log_min_duration_statement", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("120000");
    }

}
