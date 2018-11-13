package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LoggingCollector extends RawConfig {

    public LoggingCollector(Environment env) {
        super("logging_collector", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("on");
    }

}
