package br.com.selectgearmotors.bff.application.api.dto.client;

import br.com.selectgearmotors.bff.application.commons.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ClientLegalRequest", requiredProperties = {"id", "name"})
@Tag(name = "ClientLegalRequest", description = "Model")
public class ClientLegal implements Serializable {

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Fantasy Name of the Cooperative.",
            example = "SCRAP LTDA", accessMode = Schema.AccessMode.READ_ONLY)
    private String socialName;

    @Schema(description = "Fantasy Name of the Cooperative.",
            example = "SCRAP LTDA", accessMode = Schema.AccessMode.READ_ONLY)
    private String fantasyName;

    @Schema(description = "Name of the Product Category.",
            example = "Bebida", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", message = "CNPJ Inválido")
    private String companyId;

    @Schema(description = "socialIdDispatchDate of the Psychological.",
            format = "ISO8601 date string",
            example = "13/09/2022", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE_WITHOUT_TOME)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE_WITHOUT_TOME)
    private LocalDate foundationDate; //Data de Fundação

    @Schema(description = "Client of the User.",
            example = "1", ref = "ClientCategoryEntity", accessMode = Schema.AccessMode.READ_ONLY)
    private Long clientId;
}
