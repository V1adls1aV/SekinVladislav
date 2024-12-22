package hw06.service;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import hw06.Main;
import spark.template.freemarker.FreeMarkerEngine;


public final class TemplateFactory {
    public static FreeMarkerEngine freeMarkerEngine() {
        Configuration freeMarkerConfiguration = new Configuration(Configuration.VERSION_2_3_0);
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(freeMarkerConfiguration);
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Main.class, "/"));
        return freeMarkerEngine;
    }
}