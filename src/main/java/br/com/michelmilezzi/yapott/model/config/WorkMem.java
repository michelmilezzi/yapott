package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.MemoryUnityConfig;

public class WorkMem extends MemoryUnityConfig {

    private static final int NORMAL_MAINTENANCE_FACTOR = 2; 
    private static final int CONSERVATIVE_MAINTENANCE_FACTOR = 3; 
    private static final int NORMAL_PRODUCTION_FACTOR = 6; 
    private static final int CONSERVATIVE_PRODUCTION_FACTOR = 8; 
    
    private static final Long MAX_SETTING = 67108864L;
    private static final Long MIN_SETTING = 2097152L;
    
    public WorkMem(Environment env) {
        super("work_mem", env);
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

        Long workMem = calculateSetting(NORMAL_MAINTENANCE_FACTOR);
        
        if (getEnv().isConfigModeConservative()) {
            workMem = calculateSetting(CONSERVATIVE_MAINTENANCE_FACTOR);
        }      
        
        setSetting(String.valueOf(workMem));            
        
    }
    
    private void resolveSettingForProductionMode() {
        
        Long workMem = calculateSetting(NORMAL_PRODUCTION_FACTOR);
        
        if (getEnv().isConfigModeConservative()) {
            workMem = calculateSetting(CONSERVATIVE_PRODUCTION_FACTOR);
        }      
        
        if (workMem > MAX_SETTING) {
            workMem = MAX_SETTING;
        }
        
        if (workMem < MIN_SETTING) {
            workMem = MIN_SETTING;
        }
        
        setSetting(String.valueOf(workMem));     
        
    }
    
    private Long calculateSetting(int factor) {
        MaxConnections maxConnections = new MaxConnections(getEnv());
        return (getEnv().getTotalRam() / Long.valueOf(maxConnections.getSetting())) / factor;
    }

}
