package br.com.selectgearmotors.bff.application.api.resources;

import br.com.selectgearmotors.bff.application.api.dto.company.CarSeller;
import br.com.selectgearmotors.bff.application.api.dto.company.Company;
import br.com.selectgearmotors.bff.application.api.dto.company.CompanyType;
import br.com.selectgearmotors.bff.application.service.CompanyService;
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
@RequestMapping("/v1/bff/companies")
@CrossOrigin(origins = "*", allowedHeaders = "Content-Type, Authorization", maxAge = 3600)
public class CompanyResources {

    private final CompanyService companyService;

    public CompanyResources(CompanyService companyService) {
        this.companyService = companyService;
    }

    //##### Company #####
    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = CompanyResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/types")
    public ResponseEntity<CompanyType> createCompanyType(@RequestBody CompanyType companyType, @RequestHeader("Authorization") String token) {
        return companyService.createCompanyType(companyType, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CompanyResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/types")
    public ResponseEntity<List<CompanyType>> getCompanyTypes(@RequestHeader("Authorization") String token) {
        return companyService.getCompanyTypes(token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"companies", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company, @RequestHeader("Authorization") String token) {
        return companyService.createCompany(company, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"products", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CompanyResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(@RequestHeader("Authorization") String token) {
        return companyService.getCompanies(token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"products", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CompanyResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/car-sellers")
    public ResponseEntity<List<CarSeller>> getCarSellers(@RequestHeader("Authorization") String token) {
        return companyService.getCarSellers(token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"companies", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = CarSeller.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/car-sellers")
    public ResponseEntity<CarSeller> createCarSeller(@RequestBody CarSeller carSeller, @RequestHeader("Authorization") String token) {
        return companyService.createCarSeller(carSeller, token).block();
    }
    //##### Company #####
}