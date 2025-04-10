package org.mrstm.quoraapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponseDTO {
    private String title;
    private String body;
    private String username;
    private List<String> topics;
}
