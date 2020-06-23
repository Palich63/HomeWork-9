package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueRepositoryTest {

    IssueRepository repository = new IssueRepository();

    Issue issue1 = new Issue(1, false, "Петров", new TreeSet<>(Arrays.asList("bug", "invalid", "documentation")), new TreeSet<>(Arrays.asList("General", "5.7 M2")), new TreeSet<>(Arrays.asList("David", "Stefan")));
    Issue issue2 = new Issue(2, true, "Иванов", new TreeSet<>(Arrays.asList("documentation")), new TreeSet<>(Arrays.asList("General", "5.7 M2")), new TreeSet<>(Arrays.asList("David", "Andreas")));

    @BeforeEach
    void setUp() {
        ArrayList<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);

        repository.addAll(issues);
    }

    @Test
    void shouldAdd() {
        List<Issue> actual = Arrays.asList(issue1, issue2);
        assertEquals(repository.getIssues(), actual);
    }

    //Удаление по id существующего элемента
    @Test
    void shouldRemoveById() {
        List<Issue> actual = Arrays.asList(issue1);
        repository.removeById(2);
        assertEquals(repository.getIssues(), actual);
    }

    //Удаление по id не существующего элемента
    @Test
    void shouldRemoveNonExistId() {
        List<Issue> actual = Arrays.asList(issue1, issue2);
        repository.removeById(3);
        assertEquals(repository.getIssues(), actual);
    }
}