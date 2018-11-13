package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogFilename extends RawConfig {

    public LogFilename(Environment env) {
        super("log_filename", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("'postgresql-%d.log'");
    }

}
