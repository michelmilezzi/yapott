package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class CheckpointSegments extends RawConfig {
    
    public CheckpointSegments(Environment env) {
        super("checkpoint_segments", env);
    }

    @Override
    public void resolveSetting() {
        switch (getEnv().getServerMode()) {
        
        case PRODUCTION:
            resolveSettingForProductionMode();
            break;

        case DUMP:
            resolveSettingForDumpMode();
            break;

        case RESTORE:
            resolveSettingForRestoreMode();
            break;
            
        default:
            resolveSettingForProductionMode();
            break;
        }
    }

    private void resolveSettingForDumpMode() {

        String checkpointSegments = "64";
        
        if (getEnv().isConfigModeConservative()) {
            checkpointSegments = "32";
        }        
        
        setSetting(checkpointSegments);
        
    }

    private void resolveSettingForRestoreMode() {

        String checkpointSegments = "192";
        
        if (getEnv().isConfigModeConservative()) {
            checkpointSegments = "64";
        }        
        
        setSetting(checkpointSegments);        
        
    }

    private void resolveSettingForProductionMode() {
        
        String checkpointSegments = "64";
        
        if (getEnv().isConfigModeConservative()) {
            checkpointSegments = "32";
        }        
        
        setSetting(checkpointSegments);
        
    }

}
