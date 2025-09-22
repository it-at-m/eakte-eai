package de.muenchen.dms.config;

import de.muenchen.dms.common.processor.PayloadLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StartupReport {

    private static final Logger log = LoggerFactory.getLogger(StartupReport.class);

    @Autowired
    private RestTemplate restTemplate;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Service environment after startup:");
        ConfigurableEnvironment env = (ConfigurableEnvironment) event.getApplicationContext().getEnvironment();
        env.getPropertySources()
                .stream()
                .filter(ps -> ps instanceof MapPropertySource /* && ps.getName().startsWith("application.") */)
                .map(ps -> (MapPropertySource)ps)
                .forEach(mps -> {
                    log.info("------------------------------------");
                    log.info(">> property source: {}:", mps.getName());
                    mps.getSource().keySet().stream()
                            .distinct()
                            .sorted()
                            .forEach(key -> log.info("   {}={}", key, env.getProperty(key)));
                });

                String url = env.getProperty("dms.eai.service.url");
                String result = restTemplate.getForObject(url,String.class);

                log.info("******************************************");
                log.info(">> Rest Templat result",result);
                log.info("******************************************");
    }
}