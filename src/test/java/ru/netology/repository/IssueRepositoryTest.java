package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.HashSet;

class IssueRepositoryTest {

    IssueRepository issue = new IssueRepository();

    @Test
    void shouldAdd() {
        Issue issue1 = new Issue("Name", "Комментарий", "Andrey", "Vasiliy", true, HashSet);
        issue.save(issue1);

        System.out.println(issue.getIssues());
    }
}