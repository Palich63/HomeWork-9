package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    IssueManager manager = new IssueManager();

    private Issue issue1 = new Issue(1, false, "Петров", new TreeSet<>(Arrays.asList("bug", "invalid", "documentation")), new TreeSet<>(Arrays.asList("General", "5.7 M2")), new TreeSet<>(Arrays.asList("David", "Stefan")));
    private Issue issue2 = new Issue(2, true, "Иванов", new TreeSet<>(Arrays.asList("documentation")), new TreeSet<>(Arrays.asList("General", "5.7 M2")), new TreeSet<>(Arrays.asList("David", "Andreas")));
    private Issue issue3 = new Issue(3, true, "Сидоров", new TreeSet<>(Arrays.asList("bug", "duplicate")), new TreeSet<>(Arrays.asList("General Blacklog")), new TreeSet<>(Arrays.asList("Markus", "Andreas")));
    private Issue issue4 = new Issue(4, false, "Васин", new TreeSet<>(Arrays.asList("blocked")), new TreeSet<>(Arrays.asList("General Blacklog")), new TreeSet<>(Arrays.asList("Markus", "Andreas")));
    private Issue issue5 = new Issue(5, false, "Васин", new TreeSet<>(Arrays.asList("Test", "Platform")), new TreeSet<>(Arrays.asList("5.7 Blacklog")), new TreeSet<>(Arrays.asList("Michael")));

    @BeforeEach
    void setUp() {
        ArrayList<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);
        issues.add(issue5);

        manager.saveAll(issues);
    }

    //Фильтрация открытых Issue
    @Test
    void shouldFilterOpenIssue() {
        List<Issue> actual = Arrays.asList(issue2, issue3);
        assertEquals(manager.openIssue(), actual);
    }

    //Фильтрация закрытых Issue
    @Test
    void shouldFilterCloseIssue() {
        List<Issue> actual = Arrays.asList(issue1, issue4, issue5);
        assertEquals(manager.closeIssue(), actual);
    }

    //Фильтрация по автору
    @Test
    void shouldFilterByAuthor() {
        List<Issue> actual = Arrays.asList(issue4, issue5);
        assertEquals(manager.filterByAuthor("Васин"), actual);
    }

    //Фильтрация по метке
    @Test
    void shouldFilterByLabel() {
        List<Issue> actual = Arrays.asList(issue1, issue3);
        ArrayList<Issue> expected = manager.filterByLabel(new TreeSet<>(Arrays.asList("bug")));
        assertEquals(expected, actual);
    }

    //Фильтрация на кого назначено
    @Test
    void shouldFilterByAssignee() {
        List<Issue> actual = Arrays.asList(issue2, issue3, issue4);
        ArrayList<Issue> expected = manager.filterByAssignee(new TreeSet<>(Arrays.asList("Andreas")));
        assertEquals(expected, actual);
    }

    //Сортиртировка по возрастанию по id
    @Test
    void shouldSortByNewest() {
        List<Issue> actual = Arrays.asList(issue1, issue2, issue3, issue4, issue5);
        ArrayList<Issue> expected = manager.sortNewest();
        assertEquals(expected, actual);
    }

    //Сортировка по убыванию по id
    @Test
    void shouldSortByOldest() {
        List<Issue> actual = Arrays.asList(issue5, issue4, issue3, issue2, issue1);
        ArrayList<Issue> expected = manager.sortOldest();
        assertEquals(expected, actual);
    }


    //Закрытие задачи
    @Test
    void shouldClosing() {
        manager.closing(3);
        List<Issue> actual = Arrays.asList(issue2);
        assertEquals(manager.openIssue(), actual);
    }

    //Открытие задачи
    @Test
    void shouldOpening() {
        manager.opening(5);
        List<Issue> actual = Arrays.asList(issue1, issue4);
        assertEquals(manager.closeIssue(), actual);
    }
}