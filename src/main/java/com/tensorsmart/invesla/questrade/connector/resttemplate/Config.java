package com.tensorsmart.invesla.questrade.connector.resttemplate;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor.TokenInterceptor;
import com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor.LoggingInterceptor;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Config {

    @Autowired
    private LoggingInterceptor _loggingInterceptor;

    @Autowired
    private TokenInterceptor _tokenInterceptor;

    @Bean
	public RestTemplate restTemplateWithoutHeader() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(_loggingInterceptor);

        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplateWithHeader() {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(_tokenInterceptor);
        restTemplate.getInterceptors().add(_loggingInterceptor);

        return restTemplate;
    }

    void noKeyStore(HttpComponentsClientHttpRequestFactory requestFactory) {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext;

        try {
            sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            log.error(e.getMessage(), e);
            return;
        }

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        requestFactory.setHttpClient(httpClient);
    }
    
    void registerKeyStore(HttpComponentsClientHttpRequestFactory requestFactory) {
        final String keyStoreName = "cacerts";
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream keyStoreInputStream = classLoader.getResourceAsStream(keyStoreName);

        if (keyStoreInputStream == null) {
            log.error("Cannot find {}", keyStoreName);
        }

        KeyStore keystore;

        try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (GeneralSecurityException e) {
            log.error(e.getMessage(), e);
            return;
        }

        TrustManagerFactory trustManagerFactory;

        try {
            keystore.load(keyStoreInputStream, null);            
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            log.error(e.getMessage(), e);
            return;
        }

        try {
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
            return;
        }

        try {
            trustManagerFactory.init(keystore);
        } catch (KeyStoreException e) {
            log.error(e.getMessage(), e);
            return;
        }

        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

        SSLContext sc;

        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
            return;
        }

        try {
            sc.init(null, trustManagers, null);
        } catch (KeyManagementException e) {
            log.error(e.getMessage(), e);
            return;
        }

        SSLContext.setDefault(sc);

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sc);

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

        requestFactory.setHttpClient(httpClient);

        return;
    }

}
