package br.com.selectgearmotors.bff.application.api.dto.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Reservation", requiredProperties = {"vehicleId, compradorId, status"})
@Tag(name = "Reservation", description = "Model")
public class Reservation implements Serializable {

    @Schema(description = "name of the Company.",
            example = "V$")
    @NotNull(message = "o campo \"code\" é obrigario")
    private UUID vehicleId;

    @Schema(description = "name of the Company.",
            example = "V$")
    @NotNull(message = "o campo \"code\" é obrigario")
    private UUID buyerId;

    @Schema(description = "name of the Company.",
            example = "V$")
    @NotNull(message = "o campo \"code\" é obrigario")
    private StatusReservation statusReservation;

}
