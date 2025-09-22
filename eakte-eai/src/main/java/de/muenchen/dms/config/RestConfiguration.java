package de.muenchen.dms.config;

//import de.muenchen.dms.common.processor.PayloadLogger;
//import org.springframework.context.annotation.Bean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.Environment;
//import org.springframework.core.env.MapPropertySource;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.apache.hc.client5.http.auth.AuthScope;
//import org.apache.hc.client5.http.auth.CredentialsProvider;
//import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
//import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.HttpHost;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;




import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

/*
@Component
public class RestConfiguration {
    @Autowired
    Environment env;

    @Bean
    public RestTemplate restTemplate() {

        String usr = env.getProperty("dms.eai.service.username");
        String pw = env.getProperty("dms.eai.service.password");
        String host = env.getProperty("dms.eai.proxy.host");
        int port = Integer.valueOf(env.getProperty("dms.eai.proxy.port"));


        // CredentialsProvider für die Basisauthentifizierung konfigurieren
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(

                new AuthScope(host, port), // Ersetze mit deinem Host und Port
                new UsernamePasswordCredentials(usr, pw)
        );

        // HttpClient mit dem CredentialsProvider erstellen
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        // RestTemplate mit dem HttpClient erstellen
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);



        return new RestTemplate(factory);
    }
    
    
}*/

@Configuration
public class RestConfiguration {

    @Autowired
    Environment env;

    @Bean
    public RestTemplate restTemplate() {

        String usr = env.getProperty("dms.eai.service.username");
        char[] pw = env.getProperty("dms.eai.service.password").toCharArray();
        String host = env.getProperty("dms.eai.proxy.host");
        int port = Integer.valueOf(env.getProperty("dms.eai.proxy.port"));
                
        // Erstelle den CredentialsProvider für die Authentifizierung
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(host, port), // Ersetze mit deinem Host und Port
                new UsernamePasswordCredentials(usr, pw)
        );

        // Erstelle den HttpClient mit dem CredentialsProvider
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        // Erstelle die HttpComponentsClientHttpRequestFactory mit dem HttpClient
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        
        return new RestTemplate(factory);
    }
}