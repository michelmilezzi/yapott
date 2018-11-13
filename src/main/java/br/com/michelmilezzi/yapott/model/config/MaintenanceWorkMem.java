package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.MemoryUnityConfig;

public class MaintenanceWorkMem extends MemoryUnityConfig {

    private static final Long MAX_WINDOWS_SETTING = 536870912L;
    private static final int NORMAL_MAINTENANCE_FACTOR = 8;
    private static final int CONSERVATIVE_MAINTENANCE_FACTOR = 16;
    private static final int NORMAL_PRODUCTION_FACTOR = 16;
    private static final int CONSERVATIVE_PRODUCTION_FACTOR = 32;
    
    public MaintenanceWorkMem(Environment env) {
        super("maintenance_work_mem", env);
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
        resolveSetting(NORMAL_MAINTENANCE_FACTOR, CONSERVATIVE_MAINTENANCE_FACTOR);
    }

    private void resolveSettingForProductionMode() {
        resolveSetting(NORMAL_PRODUCTION_FACTOR, CONSERVATIVE_PRODUCTION_FACTOR);
    }
    
    private void resolveSetting(int normalFactor, int conservativeFactor) {
        
        Long maintenanceWorkMem = calculateSetting(normalFactor);
        
        if (getEnv().isConfigModeConservative()) {
            maintenanceWorkMem = calculateSetting(conservativeFactor);
        }      
        
        setSetting(String.valueOf(maintenanceWorkMem));     
        
    }
    
    private Long calculateSetting(int factor) {
        
        Long maintenanceWorkMem = getEnv().getTotalRam() / factor;
        
        if (getEnv().getOs().isWindows() && maintenanceWorkMem > MAX_WINDOWS_SETTING) {
            return MAX_WINDOWS_SETTING;
        }
        
        return maintenanceWorkMem;
        
    }

}
