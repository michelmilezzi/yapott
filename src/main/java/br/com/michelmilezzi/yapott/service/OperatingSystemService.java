package br.com.michelmilezzi.yapott.service;

import br.com.michelmilezzi.yapott.enumeration.Architecture;
import br.com.michelmilezzi.yapott.enumeration.OperatingSystemEngine;
import br.com.michelmilezzi.yapott.exception.YapottException;
import br.com.michelmilezzi.yapott.model.OperatingSystem;

public class OperatingSystemService {

    private static final String ARCH_64_BITS_LITERAL = "64";
    private static OperatingSystemService instance;
    
    private OperatingSystemService() {
        super();
    }
    
    public OperatingSystem resolveOperatingSystem() throws YapottException {
        
        if (isWindows()) {
            return resolveWindowsOperatingSystem();
        }
        
        if (isUnix()) {
            return resolveLinuxOperatingSystem();
        }
        
        throw new YapottException("Erro ao determinar o sistema operacional");
        
    }

    private OperatingSystem resolveLinuxOperatingSystem() {
        if (is64()) {
            return new OperatingSystem(OperatingSystemEngine.LINUX, Architecture.ARC_64);
        }
        return new OperatingSystem(OperatingSystemEngine.LINUX, Architecture.ARC_32);
    }

    private OperatingSystem resolveWindowsOperatingSystem() {
        if (is64()) {
            return new OperatingSystem(OperatingSystemEngine.WINDOWS, Architecture.ARC_64);
        }
        return new OperatingSystem(OperatingSystemEngine.WINDOWS, Architecture.ARC_32);
    }
    
    private boolean isWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.indexOf("win") >= 0;
    }

    private boolean isUnix() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0;
    }    
   
    private boolean is64() {
        return ARCH_64_BITS_LITERAL.equals(getWinArch());
    }
    
    private String getWinArch() { 
        final String arch = System.getenv("PROCESSOR_ARCHITECTURE"); 
        final String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432"); 
        final boolean isArch64 = arch != null && arch.endsWith(ARCH_64_BITS_LITERAL);
        final boolean isWowArch64 = wow64Arch != null && wow64Arch.endsWith(ARCH_64_BITS_LITERAL);
        return isArch64 || isWowArch64 ? ARCH_64_BITS_LITERAL : "32"; 
    }
    
    public static OperatingSystemService getInstance() {
        if (instance == null) {
            instance = new OperatingSystemService();
        }
        return instance;
    }
    
}
