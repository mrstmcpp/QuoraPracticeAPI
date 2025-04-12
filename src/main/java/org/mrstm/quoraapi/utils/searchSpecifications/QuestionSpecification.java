package org.mrstm.quoraapi.utils.searchSpecifications;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.mrstm.quoraapi.models.Question;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class QuestionSpecification {
    public static Specification<Question> questionSpecification(String title){
        return (root, query, criteriaBuilder) -> {
            if(title == null || title.isEmpty()){
                return null;
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%"+title.toLowerCase()+"%");
        };
    }

    public static Specification<Question> hasTopicIn(List<String> topics){
        return (root, query, criteriaBuilder) -> {
            if(topics == null || topics.isEmpty()){
                return null;
            }
            Join<Object , Object> topicJoin = root.join("topics" , JoinType.INNER);
            return topicJoin.get("name").in(topics);
        };
    }

}
