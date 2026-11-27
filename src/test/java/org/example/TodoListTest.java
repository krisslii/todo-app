package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoListTest {
    @Test
    void newListIsEmpty() {
        TodoList todoList = new TodoList();
        assertEquals(0, todoList.size());
        assertTrue(todoList.getAll().isEmpty());
    }

    @Test
    void addTrimsWhitespace() {
        TodoList todoList = new TodoList();
        todoList.add("  task one  ");
        assertEquals(1, todoList.size());
        assertEquals("task one", todoList.getAll().get(0));
    }

    @Test
    void addIgnoresNullAndEmptyValues() {
        TodoList todoList = new TodoList();
        todoList.add(null);
        todoList.add("");
        todoList.add("   ");
        todoList.add("\t");
        assertEquals(0, todoList.size());
    }

    @Test
    void removeDeletesExistingTask() {
        TodoList todoList = new TodoList();
        todoList.add("first");
        todoList.add("second");
        assertTrue(todoList.remove(0));
        assertEquals(1, todoList.size());
        assertEquals(List.of("second"), todoList.getAll());
    }

    @Test
    void removeRejectsInvalidIndexesWithoutChangingList() {
        TodoList todoList = new TodoList();
        todoList.add("task");
        assertFalse(todoList.remove(-1));
        assertFalse(todoList.remove(1));
        assertFalse(todoList.remove(10));
        assertEquals(List.of("task"), todoList.getAll());
    }

    @Test
    void removeFromEmptyListReturnsFalse() {
        TodoList todoList = new TodoList();
        assertFalse(todoList.remove(0));
    }

    @Test
    void getAllReturnsIndependentCopy() {
        TodoList todoList = new TodoList();
        todoList.add("task");
        List<String> copy = todoList.getAll();
        copy.clear();
        assertEquals(1, todoList.size());
        assertEquals(List.of("task"), todoList.getAll());
    }
}
