package br.com.selectgearmotors.bff.application.service;

import br.com.selectgearmotors.bff.application.api.dto.transaction.TransactionType;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.Brand;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.Model;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.Vehicle;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.VehicleCategory;
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

    @CircuitBreaker(name = "updateVehicleModelService", fallbackMethod = "updateVehicleModelFallback")
    @Retry(name = "updateVehicleModelService", fallbackMethod = "updateVehicleModelFallback")
    public Mono<ResponseEntity<Model>> updateVehicleModel(Long id, Model model, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(vehicleModelsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(model), Vehicle.class)
                .retrieve()
                .toEntity(Model.class);
    }

    @CircuitBreaker(name = "deleteVehicleModelService", fallbackMethod = "deleteVehicleModelFallback")
    @Retry(name = "deleteVehicleModelService", fallbackMethod = "deleteVehicleModelFallback")
    public Mono<ResponseEntity<Model>> deleteVehicleModels(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(vehicleModelsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(Model.class);
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

    @CircuitBreaker(name = "updateVehicleBrandService", fallbackMethod = "updateVehicleBrandFallback")
    @Retry(name = "updateVehicleBrandService", fallbackMethod = "updateVehicleBrandFallback")
    public Mono<ResponseEntity<Brand>> updateVehicleBrand(Long id, Brand brand, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(vehicleBrandsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(brand), Vehicle.class)
                .retrieve()
                .toEntity(Brand.class);
    }

    @CircuitBreaker(name = "deleteVehicleBrandService", fallbackMethod = "deleteVehicleBrandFallback")
    @Retry(name = "deleteVehicleBrandService", fallbackMethod = "deleteVehicleBrandFallback")
    public Mono<ResponseEntity<Brand>> deleteVehicleBrand(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(vehicleBrandsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(Brand.class);
    }

    @CircuitBreaker(name = "getVehiclesCodeService", fallbackMethod = "getVehiclesCodeFallback")
    @Retry(name = "getVehiclesCodeService", fallbackMethod = "getVehiclesCodeFallback")
    public Mono<ResponseEntity<Vehicle>> getVehiclesCode(String code, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(vehiclesUrl + "/code/" + code)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(Vehicle.class);
    }

    @CircuitBreaker(name = "updateVehicleCategoryService", fallbackMethod = "updateVehicleCategoryFallback")
    @Retry(name = "updateVehicleCategoryService", fallbackMethod = "getVehiclesCodeFallback")
    public Mono<ResponseEntity<VehicleCategory>> updateVehicleCategory(Long id, VehicleCategory vehicleCategory, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(vehicleTypesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(vehicleCategory), VehicleCategory.class)
                .retrieve()
                .toEntity(VehicleCategory.class);
    }

    @CircuitBreaker(name = "deleteVehicleCategoryService", fallbackMethod = "deleteVehicleCategoryFallback")
    @Retry(name = "deleteVehicleCategoryService", fallbackMethod = "deleteVehiclesCodeFallback")
    public Mono<ResponseEntity<VehicleCategory>> deleteVehicleCategories(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(vehicleTypesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(VehicleCategory.class);
    }

    @CircuitBreaker(name = "updateVehicleService", fallbackMethod = "updateVehicleFallback")
    @Retry(name = "updateVehicleService", fallbackMethod = "updateVehicleFallback")
    public Mono<ResponseEntity<Vehicle>> updateVehicles(Long id, Vehicle vehicle, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(vehiclesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(vehicle), Vehicle.class)
                .retrieve()
                .toEntity(Vehicle.class);
    }

    @CircuitBreaker(name = "deleteVehicleService", fallbackMethod = "deleteVehicleFallback")
    @Retry(name = "deleteVehicleService", fallbackMethod = "deleteVehicleFallback")
    public Mono<ResponseEntity<Vehicle>> deleteVehicles(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(vehiclesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(Vehicle.class);
    }

    public Mono<ResponseEntity<String>> getVehiclesReserved(String code, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(vehiclesUrl + "/" + code + "/reserved")
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(""), String.class)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<String>> getVehiclesSold(String code, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(vehiclesUrl + "/" + code + "/sold")
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(""), String.class)
                .retrieve()
                .toEntity(String.class);
    }

    public Mono<ResponseEntity<Vehicle>> getVehicleById(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(vehiclesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Vehicle.class);
    }

    public Mono<ResponseEntity<Brand>> getBrandById(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(vehicleBrandsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Brand.class);
    }

    public Mono<ResponseEntity<Model>> getModelById(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(vehicleModelsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Model.class);
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

    public Mono<ResponseEntity<List<Model>>> deleteVehicleModelFallback(String token, Throwable throwable) {
        log.error("Error on getModelsFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Model>> updateVehicleModelFallback(Long id, Model model, String token, Throwable throwable) {
        log.error("Error on updateVehicleCategoryFallback", throwable);
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

    public Mono<ResponseEntity<Brand>> updateVehicleBrandFallback(Brand brand, String token, Throwable throwable) {
        log.error("Error on createBrandsFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Brand>> deleteVehicleBrandFallback(Long id, String token, Throwable throwable) {
        log.error("Error on deleteVehicleBrandFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Vehicle>> getVehiclesCodeFallback(String code, String token, Throwable throwable) {
        log.error("Error on getVehiclesCodeFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<VehicleCategory>> updateVehicleCategoryFallback(Long id, VehicleCategory vehicleCategory, String token, Throwable throwable) {
        log.error("Error on updateVehicleCategoryFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<VehicleCategory>> deleteVehicleCategoryFallback(Long id, String token, Throwable throwable) {
        log.error("Error on deleteVehicleCategoryFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Vehicle>> updateVehicleFallback(Long id, Vehicle vehicle, String token, Throwable throwable) {
        log.error("Error on updateVehicleFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Vehicle>> deleteVehicleFallback(Long id, String token, Throwable throwable) {
        log.error("Error on deleteVehicleFallback", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

}
