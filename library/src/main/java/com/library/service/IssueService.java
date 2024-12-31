package com.library.service;

import com.library.dto.CreateIssueRequest;
import com.library.entity.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> getAllIssues();

    Issue getIssueById(Long id);

    Issue createIssue(CreateIssueRequest issue);

    Issue returnBook(Long id);

    String deleteRecord(Long id);
}