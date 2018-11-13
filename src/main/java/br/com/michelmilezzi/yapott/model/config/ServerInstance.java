package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.enumeration.Architecture;
import br.com.michelmilezzi.yapott.enumeration.Server;
import br.com.michelmilezzi.yapott.exception.YapottException;

public class ServerInstance {

    private Server server = Server.POSTGRESQL_95;   
    private Architecture architecture;
    
    public ServerInstance(Server server, Architecture architecture) throws YapottException {
        super();
        validate(server, architecture);
        this.server = server;
        this.architecture = architecture;
    }

    private void validate(Server server, Architecture architecture) throws YapottException {
        if (Server.POSTGRESQL_83 == server && Architecture.ARC_64 == architecture) {
            throw new YapottException("A versão 8.3 do PostgreSQL não possui build 64 bits");
        }
    }

    public boolean isPostgreSQL95() {
        return Server.POSTGRESQL_95 == server;
    }

    public boolean isPostgreSQL83() {
        return Server.POSTGRESQL_83 == server;
    }
    
    public boolean is64() {
        return Architecture.ARC_64 == architecture;
    }

    public boolean is32() {
        return Architecture.ARC_32 == architecture;
    }
    
}
