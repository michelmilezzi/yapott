package br.com.michelmilezzi.yapott.model;

import com.beust.jcommander.Parameter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    
}
