package br.com.michelmilezzi.yapott.service;

import java.util.ArrayList;
import java.util.List;

import br.com.michelmilezzi.yapott.model.Config;
import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.config.Autovacuum;
import br.com.michelmilezzi.yapott.model.config.CheckpointCompletionTarget;
import br.com.michelmilezzi.yapott.model.config.CheckpointSegments;
import br.com.michelmilezzi.yapott.model.config.CheckpointTimeout;
import br.com.michelmilezzi.yapott.model.config.DefaultStatisticsTarget;
import br.com.michelmilezzi.yapott.model.config.EffectiveCacheSize;
import br.com.michelmilezzi.yapott.model.config.FullPageWrites;
import br.com.michelmilezzi.yapott.model.config.ListenAddresses;
import br.com.michelmilezzi.yapott.model.config.MaintenanceWorkMem;
import br.com.michelmilezzi.yapott.model.config.MaxConnections;
import br.com.michelmilezzi.yapott.model.config.MaxWalSize;
import br.com.michelmilezzi.yapott.model.config.MinWalSize;
import br.com.michelmilezzi.yapott.model.config.Port;
import br.com.michelmilezzi.yapott.model.config.RandomPageCost;
import br.com.michelmilezzi.yapott.model.config.SharedBuffers;
import br.com.michelmilezzi.yapott.model.config.SynchronousCommit;
import br.com.michelmilezzi.yapott.model.config.WalBuffers;
import br.com.michelmilezzi.yapott.model.config.WorkMem;
import br.com.michelmilezzi.yapott.model.config.log.ClientMinMessages;
import br.com.michelmilezzi.yapott.model.config.log.LogCheckpoints;
import br.com.michelmilezzi.yapott.model.config.log.LogDuration;
import br.com.michelmilezzi.yapott.model.config.log.LogErrorVerbosity;
import br.com.michelmilezzi.yapott.model.config.log.LogFilename;
import br.com.michelmilezzi.yapott.model.config.log.LogLinePrefix;
import br.com.michelmilezzi.yapott.model.config.log.LogLockWaits;
import br.com.michelmilezzi.yapott.model.config.log.LogMinDurationStatement;
import br.com.michelmilezzi.yapott.model.config.log.LogMinErrorStatement;
import br.com.michelmilezzi.yapott.model.config.log.LogMinMessages;
import br.com.michelmilezzi.yapott.model.config.log.LogRotationAge;
import br.com.michelmilezzi.yapott.model.config.log.LogRotationSize;
import br.com.michelmilezzi.yapott.model.config.log.LogStatement;
import br.com.michelmilezzi.yapott.model.config.log.LogTempFiles;
import br.com.michelmilezzi.yapott.model.config.log.LoggingCollector;

public class ServerInstanceService {

    private static ServerInstanceService instance;
    
    private ServerInstanceService() {
        super();
    }
    
    public List<Config> configure(final Environment env) {
        if (env.getServerInstance().isPostgreSQL95()) {
            return configurePostgreSQL95(env);
        }
        return configurePostgreSQL83(env);
    }
    
    private List<Config> configurePostgreSQL83(final Environment env) {
        List<Config> configList = new ArrayList<Config>();
        configList.add(new Autovacuum(env));
        configList.add(new CheckpointCompletionTarget(env));
        configList.add(new CheckpointTimeout(env));
        configList.add(new CheckpointSegments(env));
        configList.add(new DefaultStatisticsTarget(env));
        configList.add(new EffectiveCacheSize(env));
        configList.add(new FullPageWrites(env));
        configList.add(new MaintenanceWorkMem(env));
        configList.add(new MaxConnections(env));
        configList.add(new RandomPageCost(env));
        configList.add(new SharedBuffers(env));
        configList.add(new SynchronousCommit(env));
        configList.add(new WalBuffers(env));
        configList.add(new WorkMem(env));
        
        configList.add(new ListenAddresses(env));
        configList.add(new LogStatement(env));
        configList.add(new ClientMinMessages(env));
        configList.add(new LogCheckpoints(env));
        configList.add(new LogDuration(env));
        configList.add(new LogErrorVerbosity(env));
        configList.add(new LogFilename(env));
        configList.add(new LoggingCollector(env));
        configList.add(new LogLinePrefix(env));
        configList.add(new LogLockWaits(env));
        configList.add(new LogMinDurationStatement(env));
        configList.add(new LogMinErrorStatement(env));
        configList.add(new LogMinMessages(env));
        configList.add(new LogRotationAge(env));
        configList.add(new LogRotationSize(env));
        configList.add(new LogTempFiles(env));
        
        if (env.getPort() != null) {
            configList.add(new Port(env));
        }
        
        return configList;
    }

    private List<Config> configurePostgreSQL95(final Environment env) {
        List<Config> configList = new ArrayList<Config>();
        configList.add(new Autovacuum(env));
        configList.add(new CheckpointCompletionTarget(env));
        configList.add(new CheckpointTimeout(env));
        configList.add(new EffectiveCacheSize(env));
        configList.add(new FullPageWrites(env));
        configList.add(new MaintenanceWorkMem(env));
        configList.add(new MaxConnections(env));
        configList.add(new MaxWalSize(env));
        configList.add(new MinWalSize(env));
        configList.add(new RandomPageCost(env));
        configList.add(new SharedBuffers(env));
        configList.add(new SynchronousCommit(env));
        configList.add(new WalBuffers(env));
        configList.add(new WorkMem(env));
        
        configList.add(new ListenAddresses(env));
        configList.add(new LogStatement(env));
        configList.add(new ClientMinMessages(env));
        configList.add(new LogCheckpoints(env));
        configList.add(new LogDuration(env));
        configList.add(new LogErrorVerbosity(env));
        configList.add(new LogFilename(env));
        configList.add(new LoggingCollector(env));
        configList.add(new LogLinePrefix(env));
        configList.add(new LogLockWaits(env));
        configList.add(new LogMinDurationStatement(env));
        configList.add(new LogMinErrorStatement(env));
        configList.add(new LogMinMessages(env));
        configList.add(new LogRotationAge(env));
        configList.add(new LogRotationSize(env));
        configList.add(new LogTempFiles(env));
               
        if (env.getPort() != null) {
            configList.add(new Port(env));
        }
        
        return configList;
    }
    
    public static ServerInstanceService getInstance() {
        if (instance == null) {
            instance = new ServerInstanceService();
        }
        return instance;
    }
    
}
