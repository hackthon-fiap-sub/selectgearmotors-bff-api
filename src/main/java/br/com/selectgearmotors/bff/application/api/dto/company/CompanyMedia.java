package br.com.selectgearmotors.bff.application.api.dto.company;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CompanyMedia {
    MultipartFile file;
}
