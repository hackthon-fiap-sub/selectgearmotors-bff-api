package br.com.selectgearmotors.bff.application.service;

import br.com.selectgearmotors.bff.application.api.dto.client.ClientMedia;
import br.com.selectgearmotors.bff.application.api.dto.company.CarSeller;
import br.com.selectgearmotors.bff.application.api.dto.company.Company;
import br.com.selectgearmotors.bff.application.api.dto.company.CompanyMedia;
import br.com.selectgearmotors.bff.application.api.dto.company.CompanyType;
import br.com.selectgearmotors.bff.commons.exception.ResourceFoundException;
import br.com.selectgearmotors.bff.commons.util.TokenUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class CompanyService {

    @Value("${api.company-types.url}")
    private String companyTypesUrl;

    @Value("${api.companies.url}")
    private String companiesUrl;

    @Value("${api.company-car-sellers.url}")
    private String companyCarSellersUrl;

    @Value("${api.company-media.url}")
    private String companyMediaUrl;

    private final WebClient webClient;

    public CompanyService(WebClient webClient) {
        this.webClient = webClient;
    }

    // CompanyType
    @CircuitBreaker(name = "createCompanyTypeService", fallbackMethod = "createCompanyTypeFallback")
    @Retry(name = "createCompanyTypeService", fallbackMethod = "createCompanyTypeFallback")
    public Mono<ResponseEntity<CompanyType>> createCompanyType(CompanyType companyType, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(companyTypesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(companyType), CompanyType.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(CompanyType.class);
    }

    // CompanyType
    @CircuitBreaker(name = "getCompanyTypesService", fallbackMethod = "getCompanyTypesFallback")
    @Retry(name = "getCompanyTypesService", fallbackMethod = "getCompanyTypesFallback")
    public Mono<ResponseEntity<List<CompanyType>>> getCompanyTypes(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companyTypesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(CompanyType.class);
    }

    // Company
    @CircuitBreaker(name = "companyCreateService", fallbackMethod = "companyCreateFallback")
    @Retry(name = "companyCreateService", fallbackMethod = "companyCreateFallback")
    public Mono<ResponseEntity<Company>> createCompany(Company company, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(companiesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(company), Company.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Company.class);
    }

    // Company
    @CircuitBreaker(name = "getCompaniesService", fallbackMethod = "getCompaniesServiceFallback")
    @Retry(name = "getCompaniesService", fallbackMethod = "getCompaniesServiceFallback")
    public Mono<ResponseEntity<List<Company>>> getCompanies(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companiesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(Company.class);
    }

    // CarSeller
    @CircuitBreaker(name = "getCarSellersService", fallbackMethod = "getCarSellersFallback")
    @Retry(name = "getCarSellersService", fallbackMethod = "getCarSellersFallback")
    public Mono<ResponseEntity<List<CarSeller>>> getCarSellers(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companiesUrl + "/car-sellers")
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(CarSeller.class);
    }

    // CarSeller
    @CircuitBreaker(name = "createCarSellerService", fallbackMethod = "createCarSellerFallback")
    @Retry(name = "createCarSellerService", fallbackMethod = "createCarSellerFallback")
    public Mono<ResponseEntity<CarSeller>> createCarSeller(CarSeller carSeller, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(companiesUrl + "/car-sellers")
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(carSeller), CarSeller.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(CarSeller.class);
    }

    // CompanyMedia
    @CircuitBreaker(name = "getCompanyMediaService", fallbackMethod = "getCompanyMediaFallback")
    @Retry(name = "getCompanyMediaService")
    public Mono<ResponseEntity<List<CompanyMedia>>> getCompanyMedia(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companyMediaUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(CompanyMedia.class);
    }

    public Mono<ResponseEntity<Company>> companyCreateFallback(Company company, Throwable throwable) {
        log.error("Erro ao criar empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<Company>>> getCompaniesServiceFallback(Throwable throwable) {
        log.error("Erro ao buscar empresas: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<CarSeller>>> getCarSellersFallback(Throwable throwable) {
        log.error("Erro ao buscar vendedores: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<CarSeller>> createCarSellerFallback(CarSeller carSeller, Throwable throwable) {
        log.error("Erro ao criar vendedor: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<CompanyType>> createCompanyTypeFallback(CompanyType companyType, Throwable throwable) {
        log.error("Erro ao criar tipo de empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<CompanyType>>> getCompanyTypesFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar tipos de empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<CompanyMedia>>> getCompanyMediaFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar medias de empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }
}
