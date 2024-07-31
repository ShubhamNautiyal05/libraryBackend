package com.library.controller;


import com.library.dto.CreateIssueRequest;
import com.library.entity.Issue;
import com.library.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id);
    }

    @PostMapping
    public Issue createIssue(@RequestBody CreateIssueRequest issue) {
        return issueService.createIssue(issue);
    }

    @PutMapping("/{id}/return")
    public Issue returnBook(@PathVariable Long id) {
        return issueService.returnBook(id);
    }
}