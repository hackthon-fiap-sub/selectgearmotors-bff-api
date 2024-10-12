package br.com.selectgearmotors.bff.application.service;

import br.com.selectgearmotors.bff.application.api.dto.transaction.Transaction;
import br.com.selectgearmotors.bff.application.api.dto.transaction.TransactionCreate;
import br.com.selectgearmotors.bff.application.api.dto.transaction.TransactionType;
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
public class TransactionService {

    @Value("${api.transaction-types.url}")
    private String transactionTypesUrl;

    @Value("${api.transactions.url}")
    private String transactionsUrl;

    private final WebClient webClient;

    public TransactionService(WebClient webClient) {
        this.webClient = webClient;
    }

    // TransactionType
    @CircuitBreaker(name = "createTransactionTypeService", fallbackMethod = "createTransactionTypeFallback")
    @Retry(name = "createTransactionTypeService")
    public Mono<ResponseEntity<TransactionType>> createTransactionType(TransactionType transactionType, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(transactionTypesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(transactionType), TransactionType.class)
                .retrieve()
                .toEntity(TransactionType.class);
    }

    // TransactionType
    @CircuitBreaker(name = "getTransactionTypeService", fallbackMethod = "getTransactionTypeServiceFallback")
    @Retry(name = "getTransactionTypeService")
    public Mono<ResponseEntity<List<TransactionType>>> getTransactionTypes(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(transactionTypesUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntityList(TransactionType.class);
    }

    // Transaction
    @CircuitBreaker(name = "createTransactionService", fallbackMethod = "createTransactionFallback")
    @Retry(name = "createTransactionService")
    public Mono<ResponseEntity<Transaction>> createTransaction(Transaction transaction, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(transactionsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(transaction), Transaction.class)
                .retrieve()
                .toEntity(Transaction.class);
    }

    // Transaction
    @CircuitBreaker(name = "getTransactionsService", fallbackMethod = "getTransactionsFallback")
    @Retry(name = "getTransactionsService")
    public Mono<ResponseEntity<List<Transaction>>> getTransactions(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(transactionsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntityList(Transaction.class);
    }

    // TransactionCreate
    @CircuitBreaker(name = "getTransactionCreatesService", fallbackMethod = "getTransactionCreatesFallback")
    @Retry(name = "getTransactionCreatesService")
    public Mono<ResponseEntity<List<TransactionCreate>>> getTransactionCreates(String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.get()
                .uri(transactionsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntityList(TransactionCreate.class);
    }

    // TransactionCreate
    @CircuitBreaker(name = "createTransactionCreateService", fallbackMethod = "createTransactionCreateFallback")
    @Retry(name = "createTransactionCreateService")
    public Mono<ResponseEntity<TransactionCreate>> createTransactionCreate(TransactionCreate transactionCreate, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.post()
                .uri(transactionsUrl)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(transactionCreate), TransactionCreate.class)
                .retrieve()
                .toEntity(TransactionCreate.class);
    }

    @CircuitBreaker(name = "updateTransactionCreateService", fallbackMethod = "updateTransactionCreateFallback")
    @Retry(name = "updateTransactionCreateService")
    public Mono<ResponseEntity<TransactionType>> updateTransactionType(Long id, TransactionType transactionType, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(transactionTypesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(transactionType), TransactionType.class)
                .retrieve()
                .toEntity(TransactionType.class);
    }

    @CircuitBreaker(name = "deleteTransactionCreateService", fallbackMethod = "deleteTransactionCreateFallback")
    @Retry(name = "deleteTransactionCreateService")
    public Mono<ResponseEntity<TransactionType>> deleteTransactionTypes(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(transactionTypesUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(TransactionType.class);
    }

    @CircuitBreaker(name = "updateTransactionService", fallbackMethod = "updateTransactionFallback")
    @Retry(name = "updateTransactionService")
    public Mono<ResponseEntity<Transaction>> updateTransaction(Long id, Transaction transaction, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.put()
                .uri(transactionsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(Mono.just(transaction), Transaction.class)
                .retrieve()
                .toEntity(Transaction.class);
    }

    @CircuitBreaker(name = "deleteTransactionService", fallbackMethod = "deleteTransactionFallback")
    @Retry(name = "deleteTransactionService")
    public Mono<ResponseEntity<Transaction>> deleteTransactions(Long id, String token) {
        String tokenSemBearer = TokenUtil.removeBearerPrefix(token);
        return webClient.delete()
                .uri(transactionsUrl + "/" + id)
                .headers(headers -> {
                    headers.setBearerAuth(tokenSemBearer);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .toEntity(Transaction.class);
    }

    public Mono<ResponseEntity<TransactionType>> createTransactionTypeFallback(TransactionType transactionType, String token, Throwable throwable) {
        log.error("Erro ao criar tipo de transação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<TransactionType>>> getTransactionTypeServiceFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar tipos de transação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Transaction>> createTransactionFallback(Transaction transaction, String token, Throwable throwable) {
        log.error("Erro ao criar transação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<Transaction>>> getTransactionsFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar transações", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<List<TransactionCreate>>> getTransactionCreatesFallback(String token, Throwable throwable) {
        log.error("Erro ao buscar transações de criação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<TransactionCreate>> createTransactionCreateFallback(TransactionCreate transactionCreate, String token, Throwable throwable) {
        log.error("Erro ao criar transação de criação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<TransactionType>> updateTransactionCreateFallback(Long id, TransactionType transactionType, String token, Throwable throwable) {
        log.error("Erro ao atualizar transação de criação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<TransactionType>> deleteTransactionCreateFallback(Long id, String token, Throwable throwable) {
        log.error("Erro ao deletar transação de criação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Transaction>> updateTransactionFallback(Long id, Transaction transaction, String token, Throwable throwable) {
        log.error("Erro ao atualizar transação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }

    public Mono<ResponseEntity<Transaction>> deleteTransactionFallback(Long id, String token, Throwable throwable) {
        log.error("Erro ao deletar transação", throwable);
        return Mono.just(ResponseEntity.badRequest().build());
    }
}
