package br.com.selectgearmotors.bff.application.api.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
    private Long id;
    private String status;
    private String detail;
    private String qrCodeBase64;
    private String qrCode;
    private String transactionStatus;
}