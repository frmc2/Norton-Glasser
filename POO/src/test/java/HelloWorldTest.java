import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    void testHelloWorld() {
        String mensagem = "Hello, World!";
        assertEquals("Hello, World!", mensagem);
    }
}