package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class FullPageWrites extends RawConfig {

    public FullPageWrites(Environment env) {
        super("full_page_writes", env);
    }

    @Override
    public void resolveSetting() {
        switch (getEnv().getServerMode()) {
        
        case PRODUCTION:
            setSetting("on");
            break;

        case DUMP:
            setSetting("on");
            break;

        case RESTORE:
            setSetting("off");
            break;
            
        default:
            setSetting("on");
            break;
        }
    }

}
