package io.unikube.examples.polls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/")
    @ApiOperation(value = "Welcome Page")
    public String welcome() {
        return "Welcome to the 'Polls' REST API booya! See the <a href=\"/swagger-ui/\">api documentation</a>";
    }

    @GetMapping(value = "/questions")
    @ApiOperation(value = "list all questions")
    public Iterable<Question> listQuestions(@RequestParam Map<String, String> queryParameters) {
        String text = queryParameters.getOrDefault("text", null);
        return questionService.getQuestionsByText(text);
    }

    @PostMapping(value = "/questions")
    @ApiOperation(value = "creates a new question")
    public Integer createQuestion(@RequestBody Question question) {
        System.out.println("Hallo Upload");
        Question question2 = this.questionService.createQuestion(question);
        return question2.getId();

    }

    @GetMapping(value = "/questions/{id}")
    @ApiOperation(value = "shows data of a specific question")
    public Question getQuestion(@PathVariable("id") Integer id) {
        if(id == null | id <= 0) {
            throw new IllegalArgumentException("Question existiert nicht!");
        }
        return questionService.getQuestion(id);
    }

    @PutMapping(value = "/questions/{id}")
    @ApiOperation(value = "edit an question")
    public Integer editQuestion(@RequestBody Question newQuestion, @PathVariable("id") Integer questionID) {
        Question result = this.questionService.editQuestion(newQuestion, questionID);
        return result.getId();
    }

    @DeleteMapping("/questions/{id}")
    @ApiOperation(value = "delete a question by ID")
    public String deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
        return "Question wurde gelöscht!";
    }


}
