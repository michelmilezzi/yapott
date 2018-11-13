package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogLinePrefix extends RawConfig {

    public LogLinePrefix(Environment env) {
        super("log_line_prefix", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("'%t [%p]: [%l] user=%u,db=%d,app=%a,host=%h - '");
    }

}
