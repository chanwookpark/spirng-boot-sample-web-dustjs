package io.spring.boot.sample.web.dustjs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author chanwook
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Todo implements Serializable {

    private String id;

    private String title;

    private TodoStatus status;

    private String created;

    private String dueDate;
}
