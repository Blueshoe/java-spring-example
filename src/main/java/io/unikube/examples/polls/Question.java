package io.unikube.examples.polls;

import javax.persistence.*;
import java.time.*;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table()
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @ApiModelProperty(value = "Question text")
    private String text;

    @Column
    @ApiModelProperty(value = "Publication date")
    private Date date;

    public Question(String text, Date date) {
        super();
        this.text = text;
        this.date = date;
    }

    public Question() {
        super();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
