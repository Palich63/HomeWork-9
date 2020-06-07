package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private String title;
    private String comment;
    private String author;
     private boolean status;
    private HashSet<String> label;
//    private HashSet<String> assignee;
}
