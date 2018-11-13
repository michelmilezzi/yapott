package br.com.michelmilezzi.yapott.model;

import com.beust.jcommander.Parameter;

public class Settings {

    @Parameter(names = "-D", description = "Caminho para o arquivo de configuração", required = true)
    private String configFile;

    @Parameter(names = "-conservative", description = "Modo conservador")
    private boolean conservative = false;

    @Parameter(names = "-v95", description = "Executar tuning com as regras do PostgreSQL 9.5")
    private boolean postgres95 = false;

    @Parameter(names = "-v83", description = "Executar tuning com as regras do PostgreSQL 8.3")
    private boolean postgres83 = false;

    @Parameter(names = "-64", description = "Executar tuning com as regras para arquitetura 64 do PostgreSQL")
    private boolean arch64 = false;

    @Parameter(names = "-32", description = "Executar tuning com as regras para arquitetura 32 do PostgreSQL")
    private boolean arch32 = false;

    @Parameter(names = "-p", description = "Configuração da porta que irá sobreescrever a existente")
    private Long port;    
    
    @Parameter(names = "-production", description = "Criar regras somente para modo de produção")
    private boolean production = false;
    
    @Parameter(names = "-dump", description = "Criar regras somente para modo de dump")
    private boolean dump = false;

    @Parameter(names = "-restore", description = "Criar regras somente para modo de restore")
    private boolean restore = false;
    
    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public boolean isConservative() {
        return conservative;
    }

    public void setConservative(boolean conservative) {
        this.conservative = conservative;
    }

    public boolean isPostgres95() {
        return postgres95;
    }

    public void setPostgres95(boolean postgres95) {
        this.postgres95 = postgres95;
    }

    public boolean isPostgres83() {
        return postgres83;
    }

    public void setPostgres83(boolean postgres83) {
        this.postgres83 = postgres83;
    }

    public boolean isArch64() {
        return arch64;
    }

    public void setArch64(boolean arch64) {
        this.arch64 = arch64;
    }

    public boolean isArch32() {
        return arch32;
    }

    public void setArch32(boolean arch32) {
        this.arch32 = arch32;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public boolean isProduction() {
        return production;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }

    public boolean isDump() {
        return dump;
    }

    public void setDump(boolean dump) {
        this.dump = dump;
    }

    public boolean isRestore() {
        return restore;
    }

    public void setRestore(boolean restore) {
        this.restore = restore;
    }
    
}
