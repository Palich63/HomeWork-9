package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IssueManager {

    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public boolean saveAll(List<Issue> issues) {
        return repository.addAll(issues);
    }

    // Метод Вывода открытых Issue
    public List<Issue> getOpenIssue() {
        return repository.getIssues().
                stream().
                filter(issue -> issue.isOpen())
                .collect(Collectors.toList());
    }

    // Метод Вывода закрытых Issue
    public List<Issue> getCloseIssue() {
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
    public List<Issue> sortNewest() {
        List<Issue> tmpSort = repository.getIssues();
        tmpSort.sort(Issue::compareTo);
        return tmpSort;
    }

    public List<Issue> sortOldest() {
        List<Issue> tmpSort = repository.getIssues();
        tmpSort.sort(Issue::compareTo);
        Collections.reverse(tmpSort);
        return tmpSort;
    }

//    //  Следующими двумя методами по id производим закрытие и открытие
//    public void closeById(int id) {
//        for (Issue issue : repository.getIssues()) {
//            if (issue.getId() == id) {
//                issue.setOpen(false);
//            }
//        }
//    }
//
//    public void openById(int id) {
//        for (Issue issue : repository.getIssues()) {
//            if (issue.getId() == id) {
//                issue.setOpen(true);
//            }
//        }
//    }
}
