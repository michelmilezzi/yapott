package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.converter.ByteToMegaBytesConverter;
import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.MemoryUnityConfig;

public class MinWalSize extends MemoryUnityConfig {

    private static final int DEFAULT_SEGMENT_SIZE = 16; 
    private static final Double DEFAULT_FACTOR = 0.1;
    
    public MinWalSize(Environment env) {
        super("min_wal_size", env);
    }
    
    @Override
    public void resolveSetting() {
        CheckpointSegments checkpointSegments = new CheckpointSegments(getEnv());
        final Long segment = (long) (DEFAULT_SEGMENT_SIZE * ByteToMegaBytesConverter.MEGABYTE_UNITY);
        final Long checkpointsFactor = (long) (Long.valueOf(checkpointSegments.getSetting()) * DEFAULT_FACTOR);
        setSetting(String.valueOf(checkpointsFactor * segment));
    }

}
