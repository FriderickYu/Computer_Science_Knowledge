import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.ytq.config.SpringConfig;
import org.ytq.controller.UserController;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(ctx.getBean(UserController.class));
    }
}
