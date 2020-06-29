package ru.netology.repository;

import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class IssueRepository {

    private List<Issue> issues = new ArrayList<>();

    public boolean addAll(List<Issue> issues) {
        return this.issues.addAll(issues);
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void removeById(int id) {
        issues.removeIf(issue -> issue.getId() == id);
    }

    //  Следующими двумя методами по id производим закрытие и открытие
    public void closeById(int id) {
        for (Issue issue : getIssues()) {
            if (issue.getId() == id) {
                issue.setOpen(false);
            }
        }
    }

    public void openById(int id) {
        for (Issue issue : getIssues()) {
            if (issue.getId() == id) {
                issue.setOpen(true);
            }
        }
    }
}
