package br.com.selectgearmotors.bff.application.api.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "TransactionCreateRequest", requiredProperties = {"id", "vehicleCode", "clientCode", "transactionTypeId"})
@Tag(name = "TransactionCreateRequest", description = "Model")
public class TransactionCreate implements Serializable {

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the Transaction.",
            example = "Coca-cola", accessMode = Schema.AccessMode.READ_ONLY)
    private String vehicleCode;

    @Schema(description = "Description of the Transaction.",
            example = "Coca-cola !L", accessMode = Schema.AccessMode.READ_ONLY)
    private String clientCode;

    @Schema(description = "Description of the Transaction.",
            example = "Coca-cola !L", accessMode = Schema.AccessMode.READ_ONLY)
    private String carSellerCode;

    @Schema(description = "Transaction Category of the Transaction.",
            example = "Bebida", ref = "TransactionType", accessMode = Schema.AccessMode.READ_ONLY)
    private Long transactionTypeId;

    @Schema(description = "Transaction Category of the Transaction.",
            example = "Bebida", ref = "TransactionType", accessMode = Schema.AccessMode.READ_ONLY)
    private String personType;

    @Schema(description = "Transaction Category of the Transaction.",
            example = "Bebida", ref = "TransactionType", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal price;
}
