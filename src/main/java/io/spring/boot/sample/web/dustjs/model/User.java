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
public class User implements Serializable {

    private String name;

}
