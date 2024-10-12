package br.com.selectgearmotors.bff.application.api.resources;

import br.com.selectgearmotors.bff.application.api.dto.transaction.Transaction;
import br.com.selectgearmotors.bff.application.api.dto.transaction.TransactionCreate;
import br.com.selectgearmotors.bff.application.api.dto.transaction.TransactionType;
import br.com.selectgearmotors.bff.application.service.TransactionService;
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
@RequestMapping("/v1/bff/transactions")
@CrossOrigin(origins = "*", allowedHeaders = "Content-Type, Authorization", maxAge = 3600)
public class TransactionResources {

    private final TransactionService transactionService;

    public TransactionResources(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //##### Transaction #####
    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/types")
    public ResponseEntity<TransactionType> createTransactionType(@RequestBody TransactionType transactionType, @RequestHeader("Authorization") String token) {
        return transactionService.createTransactionType(transactionType, token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PutMapping("/types/{id}")
    public ResponseEntity<TransactionType> updateTransactionType(@PathVariable("id") Long id, @RequestBody TransactionType transactionType, @RequestHeader("Authorization") String token) {
        return transactionService.updateTransactionType(id, transactionType, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/types")
    public ResponseEntity<List<TransactionType>> getTransactionTypes(@RequestHeader("Authorization") String token) {
        return transactionService.getTransactionTypes(token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @DeleteMapping("/types/{id}")
    public ResponseEntity<TransactionType> deleteTransactionTypes(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return transactionService.deleteTransactionTypes(id, token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        return transactionService.createTransaction(transaction, token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") Long id, @RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        return transactionService.updateTransaction(id, transaction, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(@RequestHeader("Authorization") String token) {
        return transactionService.getTransactions(token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @DeleteMapping
    public ResponseEntity<Transaction> deleteTransactions(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        return transactionService.deleteTransactions(id, token).block();
    }

    @Operation(summary = "Create a new Client", tags = {"products", "post"})
    @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @PostMapping("/transaction-creates")
    public ResponseEntity<TransactionCreate> createTransactionCreate(@RequestBody TransactionCreate transactionCreate, @RequestHeader("Authorization") String token) {
        return transactionService.createTransactionCreate(transactionCreate, token).block();
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TransactionResources.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
            @Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    @GetMapping("/transaction-creates")
    public ResponseEntity<List<TransactionCreate>> getTransactionCreates(@RequestHeader("Authorization") String token) {
        return transactionService.getTransactionCreates(token).block();
    }
    //##### Transaction #####

}