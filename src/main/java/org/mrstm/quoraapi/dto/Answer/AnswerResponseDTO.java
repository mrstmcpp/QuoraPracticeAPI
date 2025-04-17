package org.mrstm.quoraapi.dto.Answer;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDTO {
    private String text;
    private int questionId;
    private String answeredBy; //username

}
