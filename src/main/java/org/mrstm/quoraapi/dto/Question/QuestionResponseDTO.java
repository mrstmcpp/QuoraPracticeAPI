package org.mrstm.quoraapi.dto.Question;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponseDTO {
    private String title;
    private String body;
    private String username;
    private List<String> topics;
}
