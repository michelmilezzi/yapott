package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.MemoryUnityConfig;

public class EffectiveCacheSize extends MemoryUnityConfig {

    private static final double NORMAL_SETTING_FACTOR = 0.65;

    public EffectiveCacheSize(Environment env) {
        super("effective_cache_size", env);
    }

    @Override
    public void resolveSetting() {
        final Long effectiveCacheSize = (long) (getEnv().getTotalRam() * NORMAL_SETTING_FACTOR);
        setSetting(String.valueOf(effectiveCacheSize));        
    }

}
