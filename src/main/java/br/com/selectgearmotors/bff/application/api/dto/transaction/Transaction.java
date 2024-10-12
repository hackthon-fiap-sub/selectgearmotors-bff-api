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
@Schema(description = "Transaction", requiredProperties = {"id", "name", "description", "price", "pic", "productCategoryId", "restaurantId"})
@Tag(name = "Transaction", description = "Model")
public class Transaction implements Serializable {

    @Schema(description = "Unique identifier of the Transaction.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Vehicle Code of the Transaction.",
            example = "93e3abff-dd82-4fd3-9383-dc3695166511", accessMode = Schema.AccessMode.READ_ONLY)
    private String vehicleCode;

    @Schema(description = "Client Code of the Transaction.",
            example = "a28d7e3f-3946-4ecf-a84b-2f033fcfe576", accessMode = Schema.AccessMode.READ_ONLY)
    private String clientCode;

    @Schema(description = "Car Seller Code of the Transaction.",
            example = "d6d46512-c1c6-47bd-8af4-b9dc8ee13e2c", accessMode = Schema.AccessMode.READ_ONLY)
    private String carSellerCode;

    @Schema(description = "Price of the Transaction.",
            example = "9.00", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal price;

    @Schema(description = "Pic of the Transaction.",
            example = "PHYSICAL", accessMode = Schema.AccessMode.READ_ONLY)
    private String personType;

    @Schema(description = "Transaction Category of the Transaction.",
            example = "1", ref = "TransactionType", accessMode = Schema.AccessMode.READ_ONLY)
    private Long transactionTypeId;
}
