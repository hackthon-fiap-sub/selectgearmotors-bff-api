package br.com.selectgearmotors.bff.application.service;

import br.com.selectgearmotors.bff.application.api.dto.client.ClientLegal;
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

import java.nio.channels.MembershipKey;
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
                .uri(companyCarSellersUrl)
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
                .uri(companyCarSellersUrl)
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

    @CircuitBreaker(name = "getCompanyCodeService", fallbackMethod = "getCompanyFallback")
    @Retry(name = "getCompanyCodeService")
    public Mono<ResponseEntity<Company>> getCompanyCode(String code, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companiesUrl + "/code/" + code)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Company.class);
    }

    @CircuitBreaker(name = "getCarSellersCodeService", fallbackMethod = "getCarSellersCodeFallback")
    @Retry(name = "getCarSellersCodeService")
    public Mono<ResponseEntity<CarSeller>> getCarSellersCode(String code, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companyCarSellersUrl + "/code/" + code)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(CarSeller.class);
    }

    @CircuitBreaker(name = "updateCompanyTypeService", fallbackMethod = "updateCompanyTypeFallback")
    @Retry(name = "updateCompanyTypeService")
    public Mono<ResponseEntity<CompanyType>> updateCompanyType(Long id, CompanyType companyType, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(companyTypesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(companyType), CompanyType.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(CompanyType.class);
    }

    @CircuitBreaker(name = "deleteCompanyTypeService", fallbackMethod = "deleteCompanyTypeFallback")
    @Retry(name = "deleteCompanyTypeService")
    public Mono<ResponseEntity<CompanyType>> deleteCompanyType(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(companyTypesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(CompanyType.class);
    }

    @CircuitBreaker(name = "updateCompanyService", fallbackMethod = "updateCompanyFallback")
    @Retry(name = "updateCompanyService")
    public Mono<ResponseEntity<Company>> updateCompany(Long id, Company company, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(companiesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(company), Company.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API"))
                )
                .toEntity(Company.class);
    }

    @CircuitBreaker(name = "deleteCompanyService", fallbackMethod = "deleteCompanyFallback")
    @Retry(name = "deleteCompanyService")
    public Mono<ResponseEntity<Company>> deleteCompanies(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(companiesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API"))
                )
                .toEntity(Company.class);
    }

    @CircuitBreaker(name = "updateCarSellerService", fallbackMethod = "deleteCompanyFallback")
    @Retry(name = "updateCarSellerService")
    public Mono<ResponseEntity<CarSeller>> updateCarSeller(Long id, CarSeller carSeller, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(companyCarSellersUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(carSeller), CarSeller.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API"))
                )
                .toEntity(CarSeller.class);
    }

    @CircuitBreaker(name = "deleteCarSellersService", fallbackMethod = "deleteCarSellersFallback")
    @Retry(name = "deleteCarSellersService")
    public Mono<ResponseEntity<CarSeller>> deleteCarSellers(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(companyCarSellersUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API"))
                )
                .toEntity(CarSeller.class);
    }

    public Mono<ResponseEntity<CompanyType>> getCompanyTypeById(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companyTypesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(CompanyType.class);
    }

    public Mono<ResponseEntity<Company>> getCompanyById(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companiesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Company.class);
    }

    public Mono<ResponseEntity<CarSeller>> getCarSellerById(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(companyCarSellersUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(CarSeller.class);
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

    public Mono<ResponseEntity<Company>> getCompanyFallback(String code, String token, Throwable throwable) {
        log.error("Erro ao buscar empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<Object> getCarSellersCodeFallback(String code, String token, Throwable throwable) {
        log.error("Erro ao buscar vendedores por código: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<CompanyType>> updateCompanyTypeFallback(Long id, CompanyType companyType, Throwable throwable) {
        log.error("Erro ao atualizar tipo de empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<CompanyType>> deleteCompanyTypeFallback(Long id, Throwable throwable) {
        log.error("Erro ao deletar tipo de empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Company>> updateCompanyFallback(Long id, Company company, Throwable throwable) {
        log.error("Erro ao atualizar empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Company>> deleteCompanyFallback(Long id, Throwable throwable) {
        log.error("Erro ao deletar empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<CarSeller>> updateCarSellerFallback(Long id, CarSeller carSeller, Throwable throwable) {
        log.error("Erro ao atualizar vendedor: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

}
