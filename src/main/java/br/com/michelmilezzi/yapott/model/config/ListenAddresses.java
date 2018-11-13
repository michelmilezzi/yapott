package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class ListenAddresses extends RawConfig {

    public ListenAddresses(Environment env) {
        super("listen_addresses", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("'*'");
    }

}
