package br.com.selectgearmotors.bff.application.api.dto.vehicle;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VehicleMedia {
    MultipartFile file;
}
