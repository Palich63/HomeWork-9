package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
public class IssueRepository {

    private Collection<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public boolean addAll(ArrayList<Issue> issues) {
        return this.issues.addAll(issues);
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

    public void remove(Issue issue) {
        issues.remove(issue);
    }

    public void removeById(int id) {
        issues.removeIf(issue -> issue.getId() == id);
    }
}
