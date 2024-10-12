package br.com.selectgearmotors.bff.application.api.dto.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Schema(description = "Vehicle", requiredProperties = {"cor", "pic", "year", "description", "price", "vehicleTypeId", "brandId", "modelId", "vehicleStatus"})
@Tag(name = "Vehicle", description = "Model")
public class Vehicle implements Serializable {

    @Schema(description = "name of the Vehicle.",
            example = "Azuz", accessMode = Schema.AccessMode.READ_ONLY)
    @NotNull(message = "o campo \"cor\" é obrigario")
    @Size(min = 1, max = 255)
    private String cor;

    @Schema(description = "name of the Vehicle.",
            example = "6a5a1fd2-3b45-486b-9f60-2fa23afdc595", accessMode = Schema.AccessMode.READ_ONLY)
    private String code;

    @Schema(description = "picture of the Vehicle.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long mediaId;

    @Schema(description = "picture of the Vehicle.",
            example = "1234", accessMode = Schema.AccessMode.READ_ONLY)
    private int vehicleYear;

    @Schema(description = "picture of the Vehicle.",
            example = "45999", accessMode = Schema.AccessMode.READ_ONLY)
    private int km;

    @Schema(description = "description of the Vehicle.",
            example = "Any Description", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 0, max = 255)
    private String description;

    @Schema(description = "price of the Vehicle.",
            example = "92822.99", accessMode = Schema.AccessMode.READ_ONLY)
    @NotNull(message = "o campo \"price\" é obrigario")
    private BigDecimal price;

    @Schema(description = "Restaurant of the Vehicle.",
            example = "1", ref = "ProductCategoryEntity", accessMode = Schema.AccessMode.READ_ONLY)
    @NotNull
    private Long vehicleCategoryId;

    @Schema(description = "Restaurant of the Vehicle.",
            example = "1", ref = "RestaurantEntity", accessMode = Schema.AccessMode.READ_ONLY)
    private Long modelId;

    @Schema(description = "Status of the Vehicle.",
            example = "AVAILABLE", accessMode = Schema.AccessMode.READ_ONLY)
    private String vehicleStatus;

    @Schema(description = "Status of the Vehicle.",
            example = "CAR", accessMode = Schema.AccessMode.READ_ONLY)
    private String vehicleType;

    @Schema(description = "Status of the Vehicle.",
            example = "GASOLINE", accessMode = Schema.AccessMode.READ_ONLY)
    private String vehicleFuel;

    @Schema(description = "Status of the Vehicle.",
            example = "Uberlândia", accessMode = Schema.AccessMode.READ_ONLY)
    private String location;

    @Schema(description = "Plate of the Vehicle.",
            example = "GZK-8775", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 0, max = 255)
    private String plate;

    @Schema(description = "Chassis of the Vehicle.",
            example = "1HGCM82633A123456", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 0, max = 255)
    private String chassis;

    @Schema(description = "Renavam of the Vehicle.",
            example = "76015901475", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 0, max = 255)
    private String renavam;
}
