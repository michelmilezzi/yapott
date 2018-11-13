package br.com.michelmilezzi.yapott.service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;

import br.com.michelmilezzi.yapott.exception.YapottException;
import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.config.ServerInstance;

public class EnvironmentService {

    private static EnvironmentService instance;
    
    private EnvironmentService() {
        super();
    }
    
    public Environment resolveEnvironment(ServerInstance serverInstance) throws YapottException {
        return new Environment(OperatingSystemService.getInstance().resolveOperatingSystem(), serverInstance, getTotalMemory());
    }
    
    private Long getTotalMemory() throws YapottException {
        try {
            OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
            Method method = operatingSystemMXBean.getClass().getMethod("getTotalPhysicalMemorySize");
            method.setAccessible(true);
            return Long.parseLong(method.invoke(operatingSystemMXBean).toString());
        } catch (Exception e) {
            throw new YapottException("Erro ao verificar total de memória do sistema", e);
        }
    }
    
    public static EnvironmentService getInstance() {
        if (instance == null) {
            instance = new EnvironmentService();
        }
        return instance;
    }
    
}