package br.com.michelmilezzi.yapott.model;

import br.com.michelmilezzi.yapott.enumeration.Architecture;
import br.com.michelmilezzi.yapott.enumeration.OperatingSystemEngine;
import lombok.Getter;

@Getter
public class OperatingSystem {

    private OperatingSystemEngine engine;
    private Architecture architecture;
    
    public OperatingSystem(OperatingSystemEngine engine, Architecture architecture) {
        super();
        this.engine = engine;
        this.architecture = architecture;
    }

    public boolean isWindows() {
        return OperatingSystemEngine.WINDOWS == engine;
    }
    
    public boolean isLinux() {
        return OperatingSystemEngine.LINUX == engine;
    }
    
    public boolean is64() {
        return Architecture.ARC_64 == architecture;
    }

    public boolean is32() {
        return Architecture.ARC_32 == architecture;
    }

}
