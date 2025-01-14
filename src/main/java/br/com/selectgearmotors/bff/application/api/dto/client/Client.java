package br.com.selectgearmotors.bff.application.api.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Schema(description = "Client", requiredProperties = {"id", "name", "email", "mobile", "clientTypeId", "dataProcessingConsent", "socialId", "address", "mediaId", "description", "code", "clientTypeId"})
@Tag(name = "Client", description = "Model")
public class Client implements Serializable {

    @Schema(description = "Unique identifier of the Client.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the Client.",
            example = "José Moreira", accessMode = Schema.AccessMode.READ_ONLY)
    @NotNull(message = "o campo \"name\" é obrigario")
    @Size(min = 1, max = 255)
    private String name;

    @Schema(description = "email of the Client.",
            example = "root@localhost", accessMode = Schema.AccessMode.READ_ONLY)
    @NotNull(message = "o campo \"email\" é obrigario")
    @Size(min = 1, max = 255)
    private String email;

    @Schema(description = "Code of the Client.",
            example = "a5a8195c-c0e7-40f1-b431-85bbe7fc67a7", accessMode = Schema.AccessMode.READ_ONLY)
    private String code;

    @Schema(description = "Mobile Phone number of the Client.",
            example = "(99) 9999-9999", accessMode = Schema.AccessMode.READ_ONLY)
    @Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Mobile Phone number")
    @NotNull
    @Size(max = 15)
    private String mobile;

    @Schema(description = "Media Id of the Client.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long mediaId;

    @Schema(description = "Description of the Client.",
            example = "Any Description", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 0, max = 255)
    private String description;

    @Schema(description = "Client Type of the Client.",
            example = "1", ref = "ClientType", accessMode = Schema.AccessMode.READ_ONLY)
    private Long clientTypeId;
}