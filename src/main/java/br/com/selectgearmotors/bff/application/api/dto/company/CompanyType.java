package br.com.selectgearmotors.bff.application.api.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "CompanyTypeRequest", requiredProperties = {"id, name"})
@Tag(name = "CompanyTypeRequest", description = "Model")
public class CompanyType implements Serializable {

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the Product Category.",
            example = "Bebida", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 3, max = 255)
    private String name;
}
