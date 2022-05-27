package ru.itis.readl.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookForm {

    @NotBlank(message = "Enter the name of book")
    @Size(min = 1, max = 50, message = "Name of book should have length more than {min} and less than {max}")
    private String name;

    @Size(max = 3000, message = "Description should have length less than {max}")
    private String description;

    private MultipartFile file;

    private String[] genres;
}
