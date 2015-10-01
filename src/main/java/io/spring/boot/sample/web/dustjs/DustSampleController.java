package io.spring.boot.sample.web.dustjs;

import io.spring.boot.sample.web.dustjs.model.Todo;
import io.spring.boot.sample.web.dustjs.model.TodoStatus;
import io.spring.boot.sample.web.dustjs.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/{userName}/todos")
    public String todos(@PathVariable String userName, ModelMap model) {
        final User user = new User(userName);

        final List<Todo> todoList = new ArrayList<>();
        final String now = dateFormat(now());

        todoList.add(new Todo("1", "Eat breakfast", TodoStatus.CLOSE, now, dueDate(7, 0)));
        todoList.add(new Todo("2", "Eat lunch", TodoStatus.OPEN, now, dueDate(12, 0)));
        todoList.add(new Todo("3", "Eat supper", TodoStatus.OPEN, now, dueDate(18, 0)));
        todoList.add(new Todo("4", "Study chinese", TodoStatus.PENDING, now, dueDate(20, 0)));

        model.addAttribute("user", user);
        model.addAttribute("todoList", todoList);

        return "todo";
    }

    private String dueDate(int hour, int min) {
        LocalDateTime due = now();
        due = due.withHour(hour).withMinute(min);
        return dateFormat(due);
    }

    private String dateFormat(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

}
