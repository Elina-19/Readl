package ru.itis.readl.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.readl.validation.annotations.FileNotEmpty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FileNotEmpty(fields = "file")
public class AddChapterForm {

    @NotBlank(message = "Enter the name of chapter")
    @Size(min = 1, max = 50, message = "Length of the chapter's name should be more than {min} and less than {max}")
    private String name;

    private MultipartFile file;

}
