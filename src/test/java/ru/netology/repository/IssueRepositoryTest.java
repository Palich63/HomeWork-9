package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.manager.IssueManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueRepositoryTest {

   private IssueRepository repository = new IssueRepository();
   private IssueManager manager = new IssueManager(repository);

    private Issue issue1 = new Issue(1, false, "Петров", new TreeSet<>(Arrays.asList("bug", "invalid", "documentation")), new TreeSet<>(Arrays.asList("General", "5.7 M2")), new TreeSet<>(Arrays.asList("David", "Stefan")));
    private Issue issue2 = new Issue(2, true, "Иванов", new TreeSet<>(Arrays.asList("documentation")), new TreeSet<>(Arrays.asList("General", "5.7 M2")), new TreeSet<>(Arrays.asList("David", "Andreas")));
    private Issue issue3 = new Issue(3, true, "Сидоров", new TreeSet<>(Arrays.asList("bug", "duplicate")), new TreeSet<>(Arrays.asList("General Blacklog")), new TreeSet<>(Arrays.asList("Markus", "Andreas")));
    private Issue issue4 = new Issue(4, false, "Васин", new TreeSet<>(Arrays.asList("blocked")), new TreeSet<>(Arrays.asList("General Blacklog")), new TreeSet<>(Arrays.asList("Markus", "Andreas")));
    private Issue issue5 = new Issue(5, false, "Васин", new TreeSet<>(Arrays.asList("Test", "Platform")), new TreeSet<>(Arrays.asList("5.7 Blacklog")), new TreeSet<>(Arrays.asList("Michael")));

    @BeforeEach
    void setUp() {
        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);
        issues.add(issue5);

        repository.addAll(issues);
    }

    @Test
    void shouldAdd() {
        List<Issue> actual = Arrays.asList(issue1, issue2, issue3, issue4, issue5);
        assertEquals(repository.getIssues(), actual);
    }

    //Удаление по id существующего элемента
    @Test
    void shouldRemoveById() {
        List<Issue> actual = Arrays.asList(issue1, issue3, issue4, issue5);
        repository.removeById(2);
        assertEquals(repository.getIssues(), actual);
    }

    //Удаление по id не существующего элемента
    @Test
    void shouldRemoveNonExistId() {
        List<Issue> actual = Arrays.asList(issue1, issue2, issue3, issue4, issue5);
        repository.removeById(6);
        assertEquals(repository.getIssues(), actual);
    }

    //Закрытие задачи
    @Test
    void shouldCloseById() {
        repository.closeById(3);
        List<Issue> actual = Arrays.asList(issue2);
        assertEquals(manager.getOpenIssue(), actual);
    }

    //Открытие задачи
    @Test
    void shouldOpenById() {
        repository.openById(5);
        List<Issue> actual = Arrays.asList(issue1, issue4);
        assertEquals(manager.getCloseIssue(), actual);
    }
}