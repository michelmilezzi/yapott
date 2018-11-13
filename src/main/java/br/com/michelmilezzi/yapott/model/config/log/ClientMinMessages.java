package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class ClientMinMessages extends RawConfig {

    public ClientMinMessages(Environment env) {
        super("client_min_messages", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("warning");
    }

}
