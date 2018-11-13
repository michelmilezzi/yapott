package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class MaxConnections extends RawConfig {

    public MaxConnections(Environment env) {
        super("max_connections", env);
    }

    @Override
    public void resolveSetting() {
        switch (getEnv().getServerMode()) {
        
        case PRODUCTION:
            setSetting("80");
            break;

        case DUMP:
            setSetting("20");
            break;

        case RESTORE:
            setSetting("20");
            break;
            
        default:
            setSetting("80");
            break;
        }
    }

}
