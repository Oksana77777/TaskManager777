import org.example.Todos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.TreeSet;
class TodosTest {
    @Test
    void addTask() {
        TreeSet<String> expect = new TreeSet<>(Collections.singleton("Первая"));

        Todos todos = new Todos();
        todos.addTask("Первая");
        TreeSet<String> preference = (TreeSet<String>) todos.taskTreeSet;

        Assertions.assertEquals(expect, preference);
    }

    @Test
    void removeTask() {
        TreeSet<String> expect = new TreeSet<>(Collections.singleton("Вторая"));

        Todos todos = new Todos();
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.removeTask("Первая");
        TreeSet<String> preference = (TreeSet<String>) todos.taskTreeSet;

        Assertions.assertEquals(expect, preference);
    }

    @Test
    void getAllTasks() {
        String expect = "Вторая Первая Третья";

        Todos todos = new Todos();
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.addTask("Третья");
        String preference = todos.getAllTasks();

        Assertions.assertEquals(expect, preference);
    }

    @Test
    void restore() {
        TreeSet<String> expect = new TreeSet<>();
        expect.add("Первая");
        expect.add("Вторая");

        Todos todos = new Todos();
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.removeTask("Первая");
        todos.addTask("Третья");
        todos.restore();
        todos.restore();
        TreeSet<String> preference = (TreeSet<String>) todos.taskTreeSet;

        Assertions.assertEquals(expect, preference);
    }
}
