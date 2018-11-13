package br.com.michelmilezzi.yapott.api;

import java.io.File;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import br.com.michelmilezzi.yapott.enumeration.Architecture;
import br.com.michelmilezzi.yapott.enumeration.ConfigMode;
import br.com.michelmilezzi.yapott.enumeration.Server;
import br.com.michelmilezzi.yapott.enumeration.ServerMode;
import br.com.michelmilezzi.yapott.exception.YapottException;
import br.com.michelmilezzi.yapott.model.Config;
import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.Settings;
import br.com.michelmilezzi.yapott.model.config.ServerInstance;
import br.com.michelmilezzi.yapott.service.EnvironmentService;
import br.com.michelmilezzi.yapott.service.ServerInstanceService;
import br.com.michelmilezzi.yapott.util.Utilities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Yapott {

    private static final String PROGRAM_NAME = "Yapott";

    private Yapott() {
        super();
    }
    
    public static void main(String[] args) throws Exception {

        try {
            run(args);
        } catch (ParameterException e) {
            JCommander commander = new JCommander(new Settings());
            commander.setProgramName(PROGRAM_NAME);
            log.error("| Invalid parameter detected. ", e);
            commander.usage();
        } catch (Exception e) {
            log.error("| Unexpected error. ", e);
            throw e;
        }        
        
    }
    
    private static void run(String[] args) throws YapottException {
        Settings settings = new Settings();
        //TODO must solve this warning in future
        JCommander commander = new JCommander(settings, args);
        log.info("| Initializing Yapott. ");
        log.info("| ", commander);
        executeCommand(settings);
    }
    
    private static void executeCommand(Settings settings) throws YapottException {
        
        validate(settings);

        final ServerInstance serverInstance = createServerInstance(settings);
        final Environment env = createEnvironment(settings, serverInstance);
        
        log.info("| OS: {}", env.getOs().getEngine());
        log.info("| OS Arch: {}", env.getOs().getArchitecture());
        log.info("| Memory: {}", env.getTotalRam());
        log.info("| Config Mode: {}", env.getConfigMode());
        
        File backupFile = Utilities.createBackupFile(settings.getConfigFile());
        
        if (settings.isProduction()) {
            writeConfigs(env, ServerMode.PRODUCTION, backupFile, settings.getConfigFile());        
        }

        if (settings.isRestore()) {
            writeConfigs(env, ServerMode.RESTORE, backupFile, settings.getConfigFile());        
        }

        if (settings.isDump()) {
            writeConfigs(env, ServerMode.DUMP, backupFile,  settings.getConfigFile());        
        }
       
    }

    private static void writeConfigs(final Environment env, final ServerMode serverMode, final File backupFile, final String fileToWrite) throws YapottException {
        env.setServerMode(serverMode);
        List<Config> configs = ServerInstanceService.getInstance().configure(env);
        log.info("|---Server Mode: {}", env.getServerMode());
        for (Config config : configs) {
            log.info("| {} - {}", config.getName(), config.getFormatedSetting());
        }
        Utilities.writeToFile(backupFile, fileToWrite, configs);
    }
    
    private static Environment createEnvironment(final Settings settings, final ServerInstance serverInstance) throws YapottException {
        final Environment env = EnvironmentService.getInstance().resolveEnvironment(serverInstance);            
        if (settings.isConservative()) {
            env.setConfigMode(ConfigMode.CONSERVATIVE);
        }
        if (settings.getPort() != null) {
           env.setPort(settings.getPort()); 
        }
        return env;
    }

    private static ServerInstance createServerInstance(final Settings settings) throws YapottException {
        Server server = Server.POSTGRESQL_95;   
        Architecture architecture = Architecture.ARC_64;        
        
        if (settings.isPostgres83()) {
            server = Server.POSTGRESQL_83;
        }

        if (settings.isArch32()) {
            architecture = Architecture.ARC_32;
        }

        return new ServerInstance(server, architecture);
    }

    private static void validate(final Settings settings) throws YapottException {
        validadePostgresVersion(settings);
        validateMode(settings);
    }

    private static void validadePostgresVersion(final Settings settings) throws YapottException {
        if (!settings.isPostgres83() && !settings.isPostgres95()) {
            throw new YapottException("You must select a PostgreSQL version");
        }
        if (settings.isPostgres83() && settings.isPostgres95()) {
            throw new YapottException("You must select only one PostgreSQL version");
        }
    }

    private static void validateMode(final Settings settings) throws YapottException {
        int modeCount = 0;
        
        if (settings.isProduction()) {
            modeCount++;
        }

        if (settings.isRestore()) {
            modeCount++;
        }

        if (settings.isDump()) {
            modeCount++;
        }        
        
        if (modeCount > 1 || modeCount == 0) {
            throw new YapottException("You must select only one mode");
        }
    }
    
}
