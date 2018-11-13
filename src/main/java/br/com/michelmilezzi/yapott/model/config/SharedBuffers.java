package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.MemoryUnityConfig;

public class SharedBuffers extends MemoryUnityConfig {

    private static final double NORMAL_MAINTENANCE_FACTOR = 0.125;
    private static final double CONSERVATIVE_MAINTENANCE_FACTOR = 0.0625;
    private static final double NORMAL_PRODUCTION_FACTOR = 0.25;
    private static final double CONSERVATIVE_PRODUCTION_FACTOR = 0.125;
    private static final Long MAX_WINDOWS_SETTING = 536870912L;
    private static final Long MAX_SETTING = 8589934592L;
    private static final Long MAX_32BITS_SETTING = 2147483648L;
    private static final Long CONSERVATIVE_SETTING_BOUNDARY = 1073741824L;

    public SharedBuffers(Environment env) {
        super("shared_buffers", env);
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
    
    private void resolveSetting(double normalFactor, double conservativeFactor) {
        
        Long sharedBuffersStartPoint = calculateSetting(normalFactor);
        
        if (getEnv().isConfigModeConservative() || isTotalRamLowerThanConservativeBoundary()) {
            sharedBuffersStartPoint = calculateSetting(conservativeFactor);
        }  
        
        setSetting(String.valueOf(sharedBuffersStartPoint));     
        
    }

    private Long calculateSetting(double factor) {
        
        Long sharedBuffersStartPoint = (long) (getEnv().getTotalRam() * factor);
        
        if (getEnv().getOs().isWindows()) {
            return refineSettingForWindows(sharedBuffersStartPoint);
        }

        return refineSettingForLinux(sharedBuffersStartPoint);
        
    }
    
    private Long refineSettingForLinux(Long sharedBuffers) {
        
        if (getEnv().isSomeComponent32Bits() && sharedBuffers > MAX_32BITS_SETTING) {
            return MAX_32BITS_SETTING;
        }        
        
        if (sharedBuffers > MAX_SETTING) {
            return MAX_SETTING;
        }
        
        return sharedBuffers;
        
    }

    private Long refineSettingForWindows(Long sharedBuffers) {
        
        if (sharedBuffers > MAX_WINDOWS_SETTING) {
            return MAX_WINDOWS_SETTING;
        }
        
        return sharedBuffers;
        
    }

    private boolean isTotalRamLowerThanConservativeBoundary() {
        return getEnv().getTotalRam() <= CONSERVATIVE_SETTING_BOUNDARY;
    }

}
