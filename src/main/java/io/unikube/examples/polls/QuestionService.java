package io.unikube.examples.polls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepo;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepo = questionRepository;
    }

    public Question createQuestion(Question question) {
        return questionRepo.save(question);
    }

    public Question editQuestion(Question newQuestion, Integer id) {
        Question question = getQuestion(id);
        question.setText(newQuestion.getText());
        question.setDate(newQuestion.getDate());

        return questionRepo.save(question);

    }

    public void deleteQuestion(Integer id) {
        questionRepo.deleteById(id);
    }

    public Iterable<Question> getQuestionsByText(String text) {
        if (text == null) {
            return questionRepo.findAll();
        }
        return questionRepo.findByText(text);
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = questionRepo.findById(id);
        return question.get();
    }
}
