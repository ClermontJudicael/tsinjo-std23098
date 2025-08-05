package com.tsinjo.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VolaClient {
    private final RestTemplate restTemplate;
    private final String volaApiUrl;
    private final String apiKey;

    public VolaClient(RestTemplate restTemplate,
                      @Value("${vola.api.url}") String volaApiUrl,
                      @Value("${vola.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.volaApiUrl = volaApiUrl;
        this.apiKey = apiKey;
    }

    public VolaPaymentResponse submitPayment(VolaPaymentRequest request) {
        return restTemplate.postForObject(volaApiUrl + "/payments?apiKey=" + apiKey, request, VolaPaymentResponse.class);
    }

    public VolaPaymentResponse getPaymentStatus(String paymentId) {
        return restTemplate.getForObject(volaApiUrl + "/payments/" + paymentId + "?apiKey=" + apiKey, VolaPaymentResponse.class);
    }
}