package br.com.selectgearmotors.bff.application.api.dto.company;

import br.com.selectgearmotors.bff.application.commons.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "ProductRequest", requiredProperties = {"id", "name", "description", "price", "pic", "productCategoryId", "restaurantId"})
@Tag(name = "ProductRequest", description = "Model")
public class Company implements Serializable {

    @Schema(description = "Unique identifier of the Company.",
            example = "1")
    private Long id;

    @Schema(description = "Name of the Company.",
            example = "a5a8195c-c0e7-40f1-b431-85bbe7fc67a7", accessMode = Schema.AccessMode.READ_ONLY)
    private String code;

    @Schema(description = "Social Name of the Company.",
            example = "SCRAP LTDA", accessMode = Schema.AccessMode.READ_ONLY)
    private String socialName;

    @Schema(description = "Fantasy Name of the Company.",
            example = "SCRAP LTDA", accessMode = Schema.AccessMode.READ_ONLY)
    private String fantasyName;

    @Schema(description = "e-mail of the Company.",
            example = "root@localhost", accessMode = Schema.AccessMode.READ_ONLY)
    @NotNull(message = "o campo \"email\" é obrigario")
    @Size(min = 1, max = 255)
    private String email;

    @Schema(description = "Mobile Phone number of the Company.",
            example = "(99) 9999-9999", accessMode = Schema.AccessMode.READ_ONLY)
    @Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Mobile Phone number")
    @NotNull
    @Size(max = 15)
    private String mobile;

    @Schema(description = "Picture of the Company.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long mediaId;

    @Schema(description = "description of the Company.",
            example = "Any Description", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 0, max = 255)
    private String description;

    @Schema(description = "Address of the Company.",
            example = "Av. Jeca Junqueira, 111", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 0, max = 255)
    private String address;

    @Schema(description = "Data Processing Consent of the Company.",
            example = "true", accessMode = Schema.AccessMode.READ_ONLY)
    private Boolean dataProcessingConsent;

    @Schema(description = "Product Type of the Company.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long companyTypeId;

    @Schema(description = "Company Id of the Category.",
            example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", message = "CNPJ Inválido")
    private String companyId;

    @Schema(description = "Foundation Date of the Company.",
            format = "ISO8601 date string",
            example = "13/09/2022", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE_WITHOUT_TOME)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE_WITHOUT_TOME)
    private LocalDate foundationDate; //Data de Fundação
}
