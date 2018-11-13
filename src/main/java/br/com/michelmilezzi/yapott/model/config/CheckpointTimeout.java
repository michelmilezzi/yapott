package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.TemporalConfig;

public class CheckpointTimeout extends TemporalConfig {

    public CheckpointTimeout(Environment env) {
        super("checkpoint_timeout", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("15");
    }

}
