package com.tensorsmart.invesla.questrade.connector.resttemplate.interceptor;

import java.io.IOException;
import java.net.URI;

import com.tensorsmart.invesla.questrade.repository.entity.TokenEntity;
import com.tensorsmart.invesla.questrade.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component
public class TokenInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    TokenService _tokenService;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        return execution.execute(new UriOverrideWrapper(request), body);
    }
    
    private class UriOverrideWrapper extends HttpRequestWrapper {

        final URI _uri;
        final TokenEntity _token;

        public UriOverrideWrapper(HttpRequest request) {
            super(request);

            _token = _tokenService.getToken();
            Assert.notNull(_token, "tokenEntity should not be null.");

            getHeaders().setBearerAuth(_token.getAccessToken());

            String apiServer = StringUtils.trimTrailingCharacter(_token.getApiServer(), '/');

            _uri = URI.create(apiServer + request.getURI().toString());
        }

        @Override
        public URI getURI() {
            return _uri;
        }
    }
}
