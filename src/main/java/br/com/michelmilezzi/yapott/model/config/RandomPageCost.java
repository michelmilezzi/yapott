package br.com.michelmilezzi.yapott.model.config;

import br.com.michelmilezzi.yapott.model.Environment;
import br.com.michelmilezzi.yapott.model.RawConfig;

public class RandomPageCost extends RawConfig {

    public RandomPageCost(Environment env) {
        super("random_page_cost", env);
    }

    @Override
    public void resolveSetting() {
        setSetting("4.0");
    }

}
