package br.com.selectgearmotors.bff.application.api.resources;

import br.com.selectgearmotors.bff.application.api.dto.client.Client;
import br.com.selectgearmotors.bff.application.api.dto.reservation.Reservation;
import br.com.selectgearmotors.bff.application.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/bff/reservations")
@CrossOrigin(origins = "*", allowedHeaders = "Content-Type, Authorization", maxAge = 3600)
public class ReservationResources {

    private final ReservationService reservationService;

    public ReservationResources(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //##### Client #####
    @Operation(summary = "Create a new Client", tags = {"clients", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = ReservationResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation, @RequestHeader("Authorization") String token) {
        return reservationService.createReservation(reservation, token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"clients", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = ReservationResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation, @RequestHeader("Authorization") String token) {
        return reservationService.updateReservation(id, reservation, token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"clients", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = ReservationResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @DeleteMapping
    public ResponseEntity<Reservation> removeReservation(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return reservationService.removeReservation(id, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReservationResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(@RequestHeader("Authorization") String token) {
        return reservationService.getReservations(token).block();
    }

    @Operation(summary = "Retrieve all Client Physical", tags = {"clientPhysicals", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReservationResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/vehicle/{code}")
    public ResponseEntity<Reservation> getClientCode(@PathVariable("code") String code, @RequestHeader("Authorization") String token) {
        return reservationService.getReservationVehicleCode(code, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReservationResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return reservationService.getReservationById(id, token).block();
    }
    //##### Client #####
}