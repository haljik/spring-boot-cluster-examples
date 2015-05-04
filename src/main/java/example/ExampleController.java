package example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by haljik on 15/05/04.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("counter")
public class ExampleController {

    @ModelAttribute("counter")
    AtomicInteger counter() {
        return new AtomicInteger(0);
    }

    @RequestMapping
    String count(HttpSession session) {
        System.out.println(session.getId());
        return "counter";
    }

}
