package br.com.michelmilezzi.yapott.model;

import br.com.michelmilezzi.yapott.enumeration.ConfigMode;
import br.com.michelmilezzi.yapott.enumeration.ServerMode;
import br.com.michelmilezzi.yapott.model.config.ServerInstance;

public class Environment {

    private OperatingSystem os;
    private ConfigMode configMode = ConfigMode.NORMAL;
    private ServerMode serverMode = ServerMode.PRODUCTION;
    private ServerInstance serverInstance;
    private Long totalRam;
    private Long port;
    
    public Environment(OperatingSystem os, ServerInstance serverInstance, Long totalRam) {
        super();
        this.os = os;
        this.serverInstance = serverInstance;
        this.totalRam = totalRam;
    }

    public OperatingSystem getOs() {
        return os;
    }

    public ConfigMode getConfigMode() {
        return configMode;
    }

    public ServerMode getServerMode() {
        return serverMode;
    }

    public Long getTotalRam() {
        return totalRam;
    }

    public ServerInstance getServerInstance() {
        return serverInstance;
    }

    public boolean isConfigModeNormal() {
        return ConfigMode.NORMAL == configMode;
    }
    
    public boolean isConfigModeConservative() {
        return ConfigMode.CONSERVATIVE == configMode;
    }

    public boolean isServerModeProduction() {
        return ServerMode.PRODUCTION == serverMode;
    }

    public boolean isServerModeDump() {
        return ServerMode.DUMP == serverMode;
    }

    public boolean isServerModeRestore() {
        return ServerMode.RESTORE == serverMode;
    }

    public boolean isSomeComponent32Bits() {
        return getOs().is32() || getServerInstance().is32();
    }

    public void setConfigMode(ConfigMode configMode) {
        this.configMode = configMode;
    }

    public void setServerMode(ServerMode serverMode) {
        this.serverMode = serverMode;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }
    
}
