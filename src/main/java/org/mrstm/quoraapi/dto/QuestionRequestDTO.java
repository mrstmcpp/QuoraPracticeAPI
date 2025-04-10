package org.mrstm.quoraapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequestDTO {
    private String title;
    private String body;
    private int userid;
}
