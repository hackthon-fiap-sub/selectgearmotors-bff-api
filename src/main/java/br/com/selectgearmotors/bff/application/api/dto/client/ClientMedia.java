package br.com.selectgearmotors.bff.application.api.dto.client;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ClientMedia {
    MultipartFile file;
}
