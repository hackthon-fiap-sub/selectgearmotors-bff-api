package br.com.selectgearmotors.bff.application.api.resources;

import br.com.selectgearmotors.bff.application.api.dto.vehicle.Brand;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.Model;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.Vehicle;
import br.com.selectgearmotors.bff.application.api.dto.vehicle.VehicleCategory;
import br.com.selectgearmotors.bff.application.service.VehicleService;
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
@RequestMapping("/v1/bff/vehicles")
@CrossOrigin(origins = "*", allowedHeaders = "Content-Type, Authorization", maxAge = 3600)
public class VehicleResources {

    private final VehicleService vehicleService;

    public VehicleResources(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    //##### Vehicle Category #####
    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/categories")
    public ResponseEntity<VehicleCategory> createVehicleCategory(@RequestBody VehicleCategory vehicleCategory, @RequestHeader("Authorization") String token) {
        return vehicleService.createVehicleCategory(vehicleCategory, token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/categories/{id}")
    public ResponseEntity<VehicleCategory> updateVehicleCategory(@PathVariable("id") Long id, @RequestBody VehicleCategory vehicleCategory, @RequestHeader("Authorization") String token) {
        return vehicleService.updateVehicleCategory(id, vehicleCategory, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/categories")
    public ResponseEntity<List<VehicleCategory>> getVehicleCategories(@RequestHeader("Authorization") String token) {
        return vehicleService.getVehicleCategories(token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<VehicleCategory> deleteVehicleCategories(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return vehicleService.deleteVehicleCategories(id, token).block();
    }
    //##### Vehicle Category #####

    //##### Vehicle #####
    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle, @RequestHeader("Authorization") String token) {
        return vehicleService.createVehicles(vehicle, token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") Long id, @RequestBody Vehicle vehicle, @RequestHeader("Authorization") String token) {
        return vehicleService.updateVehicles(id, vehicle, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles(@RequestHeader("Authorization") String token) {
        return vehicleService.getVehicles(token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @DeleteMapping
    public ResponseEntity<Vehicle> deleteVehicles(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return vehicleService.deleteVehicles(id, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/code/{code}")
    public ResponseEntity<Vehicle> getVehiclesCode(@PathVariable("code") String code, @RequestHeader("Authorization") String token) {
        return vehicleService.getVehiclesCode(code, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PutMapping("/{code}/reserved")
    public ResponseEntity<String> getVehiclesReserved(@PathVariable("code") String code, @RequestHeader("Authorization") String token) {
        return vehicleService.getVehiclesReserved(code, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PutMapping("/{code}/sold")
    public ResponseEntity<String> getVehicleSold(@PathVariable("code") String code, @RequestHeader("Authorization") String token) {
        return vehicleService.getVehiclesSold(code, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return vehicleService.getVehicleById(id, token).block();
    }
    //##### Vehicle #####

    //##### Vehicle Model #####
    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/models")
    public ResponseEntity<Model> createModel(@RequestBody Model model, @RequestHeader("Authorization") String token) {
        return vehicleService.createModels(model, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/models")
    public ResponseEntity<List<Model>> getModels(@RequestHeader("Authorization") String token) {
        return vehicleService.getModels(token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PutMapping("/models/{id}")
    public ResponseEntity<Model> updateVehicleModel(@PathVariable("id") Long id, @RequestBody Model model, @RequestHeader("Authorization") String token) {
        return vehicleService.updateVehicleModel(id, model, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @DeleteMapping("/models/{id}")
    public ResponseEntity<Model> deleteVehicleModel(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return vehicleService.deleteVehicleModels(id, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/models")
    public ResponseEntity<Model> getModelById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return vehicleService.getModelById(id, token).block();
    }
    //##### Vehicle Model #####

    //##### Vehicle Brand #####
    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/brands")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand, @RequestHeader("Authorization") String token) {
        return vehicleService.createBrands(brand, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands(@RequestHeader("Authorization") String token) {
        return vehicleService.getBrands(token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PutMapping("/brands/{id}")
    public ResponseEntity<Brand> updateVehicleBrand(@PathVariable("id") Long id, @RequestBody Brand brand, @RequestHeader("Authorization") String token) {
        return vehicleService.updateVehicleBrand(id, brand, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Brand> deleteVehicleBrand(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return vehicleService.deleteVehicleBrand(id, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = VehicleResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return vehicleService.getBrandById(id, token).block();
    }
    //##### Vehicle Brand #####

}