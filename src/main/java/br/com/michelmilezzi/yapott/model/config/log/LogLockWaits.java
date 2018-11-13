package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogLockWaits extends RawConfig {

    public LogLockWaits(Environment env) {
        super("log_lock_waits", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("on");
    }

}
