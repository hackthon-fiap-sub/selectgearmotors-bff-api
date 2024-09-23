package br.com.selectgearmotors.bff.application.service;

import br.com.selectgearmotors.bff.application.api.dto.vehicle.Brand;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.Model;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.Vehicle;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.VehicleCategory;
import br.com.selectgearmotors.bff.commons.util.TokenUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class VehicleService {

    @Value("${api.vehicle-types.url}")
    private String vehicleTypesUrl;

    @Value("${api.vehicles.url}")
    private String vehiclesUrl;

    @Value("${api.vehicle-categories.url}")
    private String vehicleCategoriesUrl;

    @Value("${api.vehicle-models.url}")
    private String vehicleModelsUrl;

    @Value("${api.vehicle-brands.url}")
    private String vehicleBrandsUrl;

    private final WebClient webClient;

    public VehicleService(WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "createVehicleCategoryService", fallbackMethod = "createVehicleCategoryFallback")
    @Retry(name = "createVehicleCategoryService", fallbackMethod = "createVehicleCategoryFallback")
    public Mono<ResponseEntity<VehicleCategory>> createVehicleCategory(VehicleCategory vehicleCategory, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(vehicleTypesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(vehicleCategory), VehicleCategory.class)
                .retrieve()
                .toEntity(VehicleCategory.class);
    }

    @CircuitBreaker(name = "getVehicleCategoriesService", fallbackMethod = "getVehicleCategoriesFallback")
    @Retry(name = "status-getVehicleCategoriesService", fallbackMethod = "getVehicleCategoriesFallback")
    public Mono<ResponseEntity<List<VehicleCategory>>> getVehicleCategories(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(vehicleCategoriesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntityList(VehicleCategory.class);
    }

    @CircuitBreaker(name = "createVehiclesService", fallbackMethod = "createVehiclesFallback")
    @Retry(name = "createVehiclesService", fallbackMethod = "createVehiclesFallback")
    public Mono<ResponseEntity<Vehicle>> createVehicles(Vehicle vehicle, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(vehiclesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(vehicle), Vehicle.class)
                .retrieve()
                .toEntity(Vehicle.class);
    }

    @CircuitBreaker(name = "getVehiclesService", fallbackMethod = "getVehiclesFallback")
    @Retry(name = "getVehiclesService", fallbackMethod = "getVehiclesFallback")
    public Mono<ResponseEntity<List<Vehicle>>> getVehicles(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(vehiclesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntityList(Vehicle.class);
    }

    @CircuitBreaker(name = "createModelsService", fallbackMethod = "createModelsFallback")
    @Retry(name = "createModelsService", fallbackMethod = "createModelsFallback")
    public Mono<ResponseEntity<Model>> createModels(Model model, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(vehicleModelsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(model), Model.class)
                .retrieve()
                .toEntity(Model.class);
    }

    @CircuitBreaker(name = "getModelsService", fallbackMethod = "getModelsFallback")
    @Retry(name = "getModelsService", fallbackMethod = "getModelsFallback")
    public Mono<ResponseEntity<List<Model>>> getModels(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(vehicleModelsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntityList(Model.class);
    }

    @CircuitBreaker(name = "getBrandsService", fallbackMethod = "getBrandsFallback")
    @Retry(name = "getBrandsService", fallbackMethod = "getBrandsFallback")
    public Mono<ResponseEntity<List<Brand>>> getBrands(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(vehicleBrandsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntityList(Brand.class);
    }

    @CircuitBreaker(name = "createBrandsService", fallbackMethod = "createBrandsFallback")
    @Retry(name = "createBrandsService", fallbackMethod = "createBrandsFallback")
    public Mono<ResponseEntity<Brand>> createBrands(Brand brand, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(vehicleBrandsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(brand), Brand.class)
                .retrieve()
                .toEntity(Brand.class);
    }

    public Mono<ResponseEntity<List<VehicleCategory>>> createVehicleCategoryFallback(VehicleCategory vehicleCategory, String token, Throwable throwable) {
        log.error("Error on createVehicleCategoryFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<VehicleCategory>>> getVehicleCategoriesFallback(String token, Throwable throwable) {
        log.error("Error on getVehicleCategoriesFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Vehicle>> createVehiclesFallback(Vehicle vehicle, String token, Throwable throwable) {
        log.error("Error on createVehiclesFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<Vehicle>>> getVehiclesFallback(String token, Throwable throwable) {
        log.error("Error on getVehiclesFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Model>> createModelsFallback(Model model, String token, Throwable throwable) {
        log.error("Error on createModelsFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<Model>>> getModelsFallback(String token, Throwable throwable) {
        log.error("Error on getModelsFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<Brand>>> getBrandsFallback(String token, Throwable throwable) {
        log.error("Error on getBrandsFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Brand>> createBrandsFallback(Brand brand, String token, Throwable throwable) {
        log.error("Error on createBrandsFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }
}
