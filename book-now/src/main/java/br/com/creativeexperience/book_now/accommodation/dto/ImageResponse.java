package br.com.creativeexperience.book_now.accommodation.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

    private String imageUrl;
    
}
