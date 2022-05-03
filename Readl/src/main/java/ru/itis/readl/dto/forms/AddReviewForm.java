package ru.itis.readl.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddReviewForm {

    private Long bookId;

    @NotBlank(message = "Enter the review")
    @Size(min = 20, message = "Length of the review should be more than {min}")
    private String content;

}
