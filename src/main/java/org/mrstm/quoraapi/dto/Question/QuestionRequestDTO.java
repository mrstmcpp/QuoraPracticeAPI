package org.mrstm.quoraapi.dto.Question;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionRequestDTO {
    private String title;
    private String body;
    private int userid;
    private List<String> topics;
}
