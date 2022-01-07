package io.unikube.examples.polls;

import org.springframework.data.repository.CrudRepository;


public interface QuestionRepository extends CrudRepository<Question, Integer> {

    Iterable<Question> findByText(String text);

}
