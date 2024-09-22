package br.com.selectgearmotors.bff.application.service;

import br.com.selectgearmotors.bff.application.api.dto.client.*;
import br.com.selectgearmotors.bff.commons.exception.ResourceFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class ClientService {

    @Value("${api.client-types.url}")
    private String clientTypesUrl;

    @Value("${api.clients.url}")
    private String clientsUrl;

    @Value("${api.client-legal.url}")
    private String clientLegalUrl;

    @Value("${api.client-physical.url}")
    private String clientPhysicalUrl;

    @Value("${api.client-media.url}")
    private String clientMediaUrl;

    private final WebClient webClient;

    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    // ClientType
    @CircuitBreaker(name = "createClientTypeService", fallbackMethod = "createClientTypeFallback")
    @Retry(name = "createClientTypeService")
    public Mono<ResponseEntity<ClientType>> createClientType(ClientType clientType, String token) {
        return webClient.post()
                .uri(clientTypesUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .body(Mono.just(clientType), ClientType.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(ClientType.class);
    }

    // ClientType
    @CircuitBreaker(name = "getClientTypesService", fallbackMethod = "getClientTypesFallback")
    @Retry(name = "getClientTypesService")
    public Mono<ResponseEntity<List<ClientType>>> getClientTypes(String token) {
        return webClient.get()
                .uri(clientTypesUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(ClientType.class);
    }

    // Client
    @CircuitBreaker(name = "createClientService", fallbackMethod = "createClientFallback")
    @Retry(name = "createClientService")
    public Mono<ResponseEntity<Client>> createClient(Client client, String token) {
        return webClient.post()
                .uri(clientsUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .body(Mono.just(client), Client.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Client.class);
    }

    // Client
    @CircuitBreaker(name = "getClientsService", fallbackMethod = "getClientsFallback")
    @Retry(name = "getClientsService")
    public Mono<ResponseEntity<List<Client>>> getClients(String token) {
        return webClient.get()
                .uri(clientsUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(Client.class);
    }

    // ClientLegal
    @CircuitBreaker(name = "createClientLegalService", fallbackMethod = "createClientLegalFallback")
    @Retry(name = "createClientLegalService")
    public Mono<ResponseEntity<ClientLegal>> createClientLegal(ClientLegal clientLegalRequest, String token) {
        return webClient.post()
                .uri(clientLegalUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .body(Mono.just(clientLegalRequest), ClientLegal.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(ClientLegal.class);
    }

    // ClientLegal
    @CircuitBreaker(name = "getClientLegalsService", fallbackMethod = "getClientLegalsFallback")
    @Retry(name = "getClientLegalsService")
    public Mono<ResponseEntity<List<ClientLegal>>> getClientLegals(String token) {
        return webClient.get()
                .uri(clientLegalUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(ClientLegal.class);
    }

    // ClientPhysical
    @CircuitBreaker(name = "createClientPhysicalService", fallbackMethod = "createClientPhysicalFallback")
    @Retry(name = "createClientPhysicalService")
    public Mono<ResponseEntity<ClientPhysical>> createClientPhysical(ClientPhysical clientPhysical, String token) {
        return webClient.post()
                .uri(clientPhysicalUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .body(Mono.just(clientPhysical), ClientPhysical.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(ClientPhysical.class);
    }

    // ClientPhysical
    @CircuitBreaker(name = "getClientPhysicalsService", fallbackMethod = "getClientPhysicalsFallback")
    @Retry(name = "getClientPhysicalsService")
    public Mono<ResponseEntity<List<ClientPhysical>>> getClientPhysicals(String token) {
        return webClient.get()
                .uri(clientPhysicalUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(ClientPhysical.class);
    }

    // ClientMedia
    @CircuitBreaker(name = "getClientMediaService", fallbackMethod = "getClientMediaFallback")
    @Retry(name = "getClientMediaService")
    public Mono<ResponseEntity<List<ClientMedia>>> getClientMedia(String token) {
        return webClient.get()
                .uri(clientPhysicalUrl)
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(ClientMedia.class);
    }

    public Mono<ResponseEntity<ClientType>> createClientTypeFallback(ClientType clientType, String token, Throwable throwable) {
        log.error("Erro ao criar tipo de cliente", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<ClientLegal>> createClientLegalFallback(ClientLegal clientLegalRequest, String token, Throwable throwable) {
        log.error("Erro ao criar cliente legal", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<ClientLegal>>> getClientLegalsFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar clientes legais", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<ClientPhysical>> createClientPhysicalFallback(ClientPhysical clientPhysical, String token, Throwable throwable) {
        log.error("Erro ao criar cliente físico", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<ClientPhysical>>> getClientPhysicalsFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar clientes físicos", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<ClientType>>> getClientTypesFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar tipos de clientes", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Client>> createClientFallback(Client client, String token, Throwable throwable) {
        log.error("Erro ao criar cliente", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<Client>>> getClientsFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar clientes", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<ClientMedia>>> getClientMediaFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar medias", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }
}
