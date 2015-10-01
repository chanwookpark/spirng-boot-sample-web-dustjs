package io.spring.boot.sample.web.dustjs;

import io.spring.boot.sample.web.dustjs.model.Todo;
import io.spring.boot.sample.web.dustjs.model.TodoStatus;
import io.spring.boot.sample.web.dustjs.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

/**
 * @author chanwook
 */
@Controller
public class DustSampleController {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    @RequestMapping("/hello")
    public String hello(ModelMap model) {
        model.put("title", "Greeting!!");
        return "hello";
    }

    @RequestMapping("/partial")
    public String partial() {
        return "master";
    }

    @RequestMapping("/todos")
    public String jsonModel(ModelMap model) {
        final User user = new User("chanwook");

        final List<Todo> todos = new ArrayList<>();

        todos.add(new Todo("1", "Eat 아침", TodoStatus.CLOSE, dateFormat(now()), dateFormat(dueDate(7, 0))));
        todos.add(new Todo("2", "Eat 점심", TodoStatus.OPEN, dateFormat(now()), dateFormat(dueDate(12, 0))));
        todos.add(new Todo("3", "Eat 저녁", TodoStatus.OPEN, dateFormat(now()), dateFormat(dueDate(18, 0))));
        todos.add(new Todo("4", "Study chinese", TodoStatus.PENDING, dateFormat(now()), dateFormat(dueDate(20, 0))));

        model.addAttribute("user", user);
        model.addAttribute("todos", todos);

        return "todo";
    }

    private String dateFormat(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

    private LocalDateTime dueDate(int hour, int min) {
        LocalDateTime due = now();
        due = due.withHour(hour).withMinute(min);
        return due;
    }

}
