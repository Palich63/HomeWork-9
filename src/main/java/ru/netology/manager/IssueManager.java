package ru.netology.manager;

import lombok.AllArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

@AllArgsConstructor
public class IssueManager {

    private IssueRepository repository = new IssueRepository();

    public IssueManager() {

    }

    public void save(Issue issue) {
        repository.save(issue);
    }

    public boolean saveAll(ArrayList<Issue> issues) {
        return repository.addAll(issues);
    }

    public ArrayList<Issue> allIssue() {
        return (ArrayList<Issue>) repository.getIssues();
    }

    // Метод Вывода открытых Issue
    // ??интересно, а можно с помощью лямбда выражения записать этот метод и сделать ещё короче
    public ArrayList<Issue> openIssue() {
        ArrayList<Issue> opened = new ArrayList<>();
        for (Issue issue : repository.getIssues()) {
            if (issue.isOpen()) {
                opened.add(issue);
            }
        }
        return opened;
    }

    // Метод Вывода закрытых Issue
    public ArrayList<Issue> closeIssue() {
        ArrayList<Issue> closed = new ArrayList<>();
        for (Issue issue : repository.getIssues()) {
            if (issue.isOpen() != true) {
                closed.add(issue);
            }
        }
        return closed;
    }

    //Фильтр по автору
    public ArrayList<Issue> filterByAuthor(String author) {
        Predicate<String> byAuthor = Predicate.isEqual(author);
        ArrayList<Issue> tmpFilter = new ArrayList<>();
        for (Issue issue : repository.getIssues()) {
            if (byAuthor.test(issue.getAuthor())) {
                tmpFilter.add(issue);
            }
        }
        return tmpFilter;
    }

    // Фильт по меткам
    public ArrayList<Issue> filterByLabel(Set<String> label) {
        Predicate<Set<String>> byLabel = l -> l.containsAll(label);
        ArrayList<Issue> tmpFilter = new ArrayList<>();
        for (Issue issue : repository.getIssues()) {
            if (byLabel.test(issue.getLabel())) {
                tmpFilter.add(issue);
            }
        }
        return tmpFilter;
    }

    //Фильтр на кого назначено
    public ArrayList<Issue> filterByAssignee(Set<String> assignee) {
        Predicate<Set<String>> byAssignee = l -> l.containsAll(assignee);
        ArrayList<Issue> tmpFilter = new ArrayList<>();
        for (Issue issue : repository.getIssues()) {
            if (byAssignee.test(issue.getAssignee())) {
                tmpFilter.add(issue);
            }
        }
        return tmpFilter;
    }

    // Следующие два метода сортируют список по возрастанию и убыванию по id
    public ArrayList<Issue> sortNewest() {
        ArrayList<Issue> tmpSort = new ArrayList<>();
        tmpSort.addAll(repository.getIssues());
        Collections.sort(tmpSort, Issue::compareTo);
        return tmpSort;
    }

    public ArrayList<Issue> sortOldest() {
        ArrayList<Issue> tmpSort = new ArrayList<>();
        tmpSort.addAll(repository.getIssues());
        Collections.sort(tmpSort, Issue::compareTo);
        Collections.reverse(tmpSort);
        return tmpSort;
    }

    //  Следующими двумя методами по id производим закрытие и открытие
    public void closing(int id) {
        Predicate<Integer> byId = Predicate.isEqual(id);
        for (Issue issue : repository.getIssues()) {
            if (byId.test(issue.getId())) {
                issue.setOpen(false);
            }
        }
    }

    public void opening(int id) {
        Predicate<Integer> byId = Predicate.isEqual(id);
        for (Issue issue : repository.getIssues()) {
            if (byId.test(issue.getId())) {
                issue.setOpen(true);
            }
        }
    }
}
