package org.mrstm.quoraapi.dto.Answer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnswerAddRequestDTO {
    private int userid;
    @NotBlank(message = "Answer could not be blank.")
    private String text;
}
