package br.com.michelmilezzi.yapott.model;

import java.text.MessageFormat;

import br.com.michelmilezzi.yapott.converter.ByteToMegaBytesConverter;

public abstract class MemoryUnityConfig extends Config {

    public MemoryUnityConfig(String name, Environment env) {
        super(name, env);
    }

    @Override
    public String getFormatedSetting() {
        final Long setting = ByteToMegaBytesConverter.getInstance().convert(Long.valueOf(getSetting()));
        return MessageFormat.format("{0,number,#}MB", setting);
    }
    
    public abstract void resolveSetting();

}
