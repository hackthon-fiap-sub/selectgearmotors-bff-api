package br.com.selectgearmotors.bff.application.api.dto.vehicle;

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
@Schema(description = "ModelRequest", requiredProperties = {"id, name"})
@Tag(name = "ModelRequest", description = "Model")
public class Model implements Serializable {

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the Model.",
            example = "Seven Food", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 3, max = 255)
    private String name;

    @Schema(description = "Name of the Model.",
            example = "Seven Food", accessMode = Schema.AccessMode.READ_ONLY)
    private Long brandId;
}