package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class CheckpointCompletionTarget extends RawConfig {

    public CheckpointCompletionTarget(Environment env) {
        super("checkpoint_completion_target", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("0.9");
    }

}
