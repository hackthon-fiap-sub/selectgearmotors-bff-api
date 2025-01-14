package br.com.selectgearmotors.bff.application.service;

import br.com.selectgearmotors.bff.application.api.dto.client.Client;
import br.com.selectgearmotors.bff.application.api.dto.client.ClientLegal;
import br.com.selectgearmotors.bff.application.api.dto.company.Company;
import br.com.selectgearmotors.bff.application.api.dto.company.CompanyType;
import br.com.selectgearmotors.bff.application.api.dto.reservation.Reservation;
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
public class ReservationService {

    @Value("${api.reservations.url}")
    private String reservationsUrl;

    private final WebClient webClient;

    public ReservationService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Reservation
    @CircuitBreaker(name = "getReservationsService", fallbackMethod = "getReservationsServiceFallback")
    @Retry(name = "getReservationsService", fallbackMethod = "getReservationsServiceFallback")
    public Mono<ResponseEntity<List<Reservation>>> getReservations(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(reservationsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntityList(Reservation.class);
    }

    // Reservation
    @CircuitBreaker(name = "reservationCreateService", fallbackMethod = "reservationCreateFallback")
    @Retry(name = "reservationCreateService", fallbackMethod = "reservationCreateFallback")
    public Mono<ResponseEntity<Reservation>> createReservation(Reservation reservation, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(reservationsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(reservation), Reservation.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Reservation.class);
    }

    @CircuitBreaker(name = "updateReservationService", fallbackMethod = "updateReservationFallback")
    @Retry(name = "updateReservationService")
    public Mono<ResponseEntity<Reservation>> updateReservation(Long id, Reservation reservation, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(reservationsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(reservation), Reservation.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API"))
                )
                .toEntity(Reservation.class);
    }

    @CircuitBreaker(name = "deleteReservationService", fallbackMethod = "deleteReservationFallback")
    @Retry(name = "deleteReservationService")
    public Mono<ResponseEntity<Reservation>> removeReservation(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(reservationsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API"))
                )
                .toEntity(Reservation.class);
    }

    @CircuitBreaker(name = "getReservationCodeService", fallbackMethod = "getReservationFallback")
    @Retry(name = "getReservationCodeService")
    public Mono<ResponseEntity<Reservation>> getReservationVehicleCode(String code, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(reservationsUrl + "/code/" + code)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Reservation.class);
    }

    public Mono<ResponseEntity<Reservation>> getReservationById(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(reservationsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(new ResourceFoundException("Erro na API")))
                .toEntity(Reservation.class);
    }

    public Mono<ResponseEntity<Reservation>> reservationCreateFallback(Reservation reservation, Throwable throwable) {
        log.error("Erro ao criar empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<Reservation>>> getReservationsServiceFallback(Throwable throwable) {
        log.error("Erro ao buscar empresas: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Reservation>> updateReservationFallback(Long id, Reservation reservation, Throwable throwable) {
        log.error("Erro ao atualizar tipo de empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Reservation>> deleteReservationFallback(Long id, Throwable throwable) {
        log.error("Erro ao deletar empresa: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Reservation>> getReservationFallback(String code, Throwable throwable) {
        log.error("Erro ao buscar empresa por código: {}", throwable.getMessage());
        return Mono.just(ResponseEntity.badRequest().build());
    }

}
