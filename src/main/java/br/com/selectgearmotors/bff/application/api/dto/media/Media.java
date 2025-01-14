package br.com.selectgearmotors.bff.application.api.dto.media;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
@Tag(name = "Media object")
public class Media implements Serializable {

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", required = true)
    private String name;

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", required = true)
    private String path;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
}
