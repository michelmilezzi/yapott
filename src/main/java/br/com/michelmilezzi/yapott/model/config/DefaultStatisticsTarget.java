package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class DefaultStatisticsTarget extends RawConfig {

    public DefaultStatisticsTarget(Environment env) {
        super("default_statistics_target", env);
    }

    @Override
    public void resolveSetting() {
        if (getEnv().getServerInstance().isPostgreSQL83()) {
            setSetting("100");
        }
    }

}
