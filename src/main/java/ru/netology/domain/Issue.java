package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue implements Comparable<Issue> {

    private int id;
    private boolean isOpen;
    private String author;
    private Set<String> label;
    private Set<String> milestone;
    private Set<String> assignee;

    @Override
    public int compareTo(Issue issue) {
        return id - issue.id;
    }
}
