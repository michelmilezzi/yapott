package br.com.michelmilezzi.yapott.model;

import com.beust.jcommander.Parameter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Settings {

    @Parameter(names = "-D", description = "Path to PostgreSQL's configuration file", required = true)
    private String configFile;

    @Parameter(names = "-conservative", description = "Use non-aggressive rules")
    private boolean conservative = false;

    @Parameter(names = "-v95", description = "Use PostgreSQL 9.5 rules")
    private boolean postgres95 = false;

    @Parameter(names = "-v83", description = "Use PostgreSQL 8.3 rules")
    private boolean postgres83 = false;

    @Parameter(names = "-64", description = "Use PostgreSQL 64-bit rules")
    private boolean arch64 = false;

    @Parameter(names = "-32", description = "Use PostgreSQL 32-bit rules")
    private boolean arch32 = false;

    @Parameter(names = "-p", description = "Server port")
    private Long port;    
    
    @Parameter(names = "-production", description = "Improve production performance")
    private boolean production = false;
    
    @Parameter(names = "-dump", description = "Improve dump speed")
    private boolean dump = false;

    @Parameter(names = "-restore", description = "Improve restore performance")
    private boolean restore = false;
    
}
