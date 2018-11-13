package br.com.michelmilezzi.yapott.model.config.log;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class LogTempFiles extends RawConfig {

    public LogTempFiles(Environment env) {
        super("log_temp_files", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("16MB");
    }

}
