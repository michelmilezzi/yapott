package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.MemoryUnityConfig;

public class WalBuffers extends MemoryUnityConfig {

    private static final int NORMAL_MAINTENANCE_FACTOR = 16;
    private static final int CONSERVATIVE_MAINTENANCE_FACTOR = 32;
    private static final int NORMAL_PRODUCTION_FACTOR = 32;
    private static final int CONSERVATIVE_PRODUCTION_FACTOR = 48;
    private static final Long MAX_SETTING = 16777216L;
    
    public WalBuffers(Environment env) {
        super("wal_buffers", env);
    }

    @Override
    public void resolveSetting() {
        switch (getEnv().getServerMode()) {
        
        case PRODUCTION:
            resolveSettingForProductionMode();
            break;

        case DUMP:
            resolveSettingForMaintenanceMode();
            break;

        case RESTORE:
            resolveSettingForMaintenanceMode();
            break;
            
        default:
            resolveSettingForProductionMode();
            break;
        }
    }

    private void resolveSettingForMaintenanceMode() {

        Long walBuffers = calculateSetting(NORMAL_MAINTENANCE_FACTOR);
        
        if (getEnv().isConfigModeConservative()) {
            walBuffers = calculateSetting(CONSERVATIVE_MAINTENANCE_FACTOR);
        }      
        
        setSetting(String.valueOf(walBuffers));            
        
    }

    private void resolveSettingForProductionMode() {
        
        Long walBuffers = calculateSetting(NORMAL_PRODUCTION_FACTOR);
        
        if (getEnv().isConfigModeConservative()) {
            walBuffers = calculateSetting(CONSERVATIVE_PRODUCTION_FACTOR);
        }      
        
        if (walBuffers > MAX_SETTING) {
            walBuffers = MAX_SETTING;
        }
        
        setSetting(String.valueOf(walBuffers));     
        
    }
    
    private Long calculateSetting(int factor) {
        SharedBuffers sharedBuffers = new SharedBuffers(getEnv());
        return Long.valueOf(sharedBuffers.getSetting()) / factor;
    }
    
}
