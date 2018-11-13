package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogMinMessages extends RawConfig {

    public LogMinMessages(Environment env) {
        super("log_min_messages", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("warning");
    }

}
