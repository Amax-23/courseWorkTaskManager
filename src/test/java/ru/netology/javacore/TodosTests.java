package ru.netology.javacore;

import org.junit.jupiter.api.*;


@DisplayName("Тестирование: Todos")
public class TodosTests {
    Todos todos = new Todos();
    TodoServer server = new TodoServer(8989, todos);

    @BeforeAll
    static void setUpApp() {
        System.out.println("Начинаем тестирование.");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Тест запускается.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Тест завершен ОК");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Все тесты пройдены.");
    }

    @Test
    @DisplayName("Тестирование restoreTask")
    void restoreTask() {
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.removeTask("Первая");
        todos.addTask("Третья");
        todos.restoreTask();
        todos.restoreTask();
        Assertions.assertEquals(2, todos.getList().size());
        Assertions.assertTrue(todos.getList().contains("Вторая"));
        Assertions.assertTrue(todos.getList().contains("Первая"));
    }

    @Test
    @DisplayName("Тестирование addTask")
    void addTask() {
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.addTask("Третья");
        todos.addTask("Четвертая");
        todos.addTask("Пятая");
        todos.addTask("Шестая");
        todos.addTask("Седьмая");
        todos.addTask("Восьмая");
        Assertions.assertEquals(todos.getMaxSizeList(), todos.getList().size());
        Assertions.assertTrue(todos.getList().contains("Седьмая"));
        Assertions.assertFalse(todos.getList().contains("Восьмая"));
    }

    @Test
    @DisplayName("Тестирование removeTask")
    void removeTask() {
        todos.addTask("Первая");
        todos.addTask("Вторая");
        todos.addTask("Третья");
        todos.addTask("Четвертая");
        todos.addTask("Пятая");
        Assertions.assertTrue(todos.getList().contains("Третья"));
        Assertions.assertEquals(5, todos.getList().size());
        todos.removeTask("Третья");
        Assertions.assertEquals(4, todos.getList().size());
        Assertions.assertFalse(todos.getList().contains("Третья"));
        todos.removeTask("Первая");
        Assertions.assertEquals(3, todos.getList().size());
        Assertions.assertFalse(todos.getList().contains("Первая"));
    }
}
