package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.converter.ByteToMegaBytesConverter;
import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.MemoryUnityConfig;

public class MaxWalSize extends MemoryUnityConfig {

    private static final int DEFAULT_SEGMENT_SIZE = 16; 
    
    public MaxWalSize(Environment env) {
        super("max_wal_size", env);
    }

    @Override
    public void resolveSetting() {
        CheckpointSegments checkpointSegments = new CheckpointSegments(getEnv());
        setSetting(String.valueOf(Long.valueOf(checkpointSegments.getSetting()) * DEFAULT_SEGMENT_SIZE * ByteToMegaBytesConverter.MEGABYTE_UNITY));
    }

}
