package br.com.selectgearmotors.bff.application.api.resources;

import br.com.selectgearmotors.bff.application.api.dto.client.Client;
import br.com.selectgearmotors.bff.application.api.dto.client.ClientLegal;
import br.com.selectgearmotors.bff.application.api.dto.client.ClientPhysical;
import br.com.selectgearmotors.bff.application.api.dto.client.ClientType;
import br.com.selectgearmotors.bff.application.service.ClientService;
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
@RequestMapping("/v1/bff/clients")
@CrossOrigin(origins = "*", allowedHeaders = "Content-Type, Authorization", maxAge = 3600)
public class ClientResources {

    private final ClientService clientService;

    public ClientResources(ClientService clientService) {
        this.clientService = clientService;
    }

    //##### Client #####
    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/types")
    public ResponseEntity<ClientType> createClientType(@RequestBody ClientType clientType,@RequestHeader("Authorization") String token) {
        return clientService.createClientType(clientType, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/types")
    public ResponseEntity<List<ClientType>> getClientTypes(@RequestHeader("Authorization") String token) {
        return clientService.getClientTypes(token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"clients", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client, @RequestHeader("Authorization") String token) {
        return clientService.createClient(client, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping
    public ResponseEntity<List<Client>> getClients(@RequestHeader("Authorization") String token) {
        return clientService.getClients(token).block();
    }

    @Operation(summary = "Create a new Client Legal", tags = {"client Legals", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/legals")
    public ResponseEntity<ClientLegal> createClientLegal(@RequestBody ClientLegal clientLegalRequest, @RequestHeader("Authorization") String token) {
        return clientService.createClientLegal(clientLegalRequest, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/legals")
    public ResponseEntity<List<ClientLegal>> getClientLegals(@RequestHeader("Authorization") String token) {
        return clientService.getClientLegals(token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"clients", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/physicals")
    public ResponseEntity<ClientPhysical> createClientPhysical(@RequestBody ClientPhysical clientPhysical, @RequestHeader("Authorization") String token) {
        return clientService.createClientPhysical(clientPhysical, token).block();
    }

    @Operation(summary = "Retrieve all Client Physical", tags = {"clientPhysicals", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/physicals")
    public ResponseEntity<List<ClientPhysical>> getClientPhysicals(@RequestHeader("Authorization") String token) {
        return clientService.getClientPhysicals(token).block();
    }
    //##### Client #####

}