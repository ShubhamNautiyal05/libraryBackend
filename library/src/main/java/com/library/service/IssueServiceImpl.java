package com.library.service;

import com.library.dto.CreateIssueRequest;
import com.library.entity.Book;
import com.library.entity.Issue;
import com.library.entity.Student;
import com.library.repo.BookRepo;
import com.library.repo.IssueRepository;
import com.library.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final BookRepo bookRepo;
    private final StudentRepository studentRepository;

    @Override
    public List<Issue> getAllIssues() {
        List<Issue> allIssueRecords = issueRepository.findAll();
        if (CollectionUtils.isEmpty(allIssueRecords)){
            throw new IllegalArgumentException("No record found");
        } else {
            return allIssueRecords;
        }
    }

    @Override
    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No record found"));
    }

    @Override
    public Issue createIssue(CreateIssueRequest request) {
        try {
            Issue issue = new Issue();
            issue.setIssueDate(LocalDate.now());
            Book book = bookRepo.findByIsbnAndIssued(request.getBook(), false).orElseThrow(()-> new IllegalArgumentException("No book record found"));
            Student student = studentRepository.findByEmail(request.getStudent()).orElseThrow(() -> new IllegalArgumentException("No student details found"));
            book.setIssued(true);
            bookRepo.save(book);
            issue.setBook(book);
            issue.setStudent(student);
            return issueRepository.save(issue);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Issue details not saved. Please try again");
        }
    }

    @Override
    public Issue returnBook(Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No record found"));
        if (issue != null) {
            issue.setReturnDate(LocalDate.now());
            Book book = bookRepo.findById(issue.getBook().getId()).orElseThrow(() -> new IllegalArgumentException("No book found"));
            book.setIssued(false);
            bookRepo.save(book);
            return issueRepository.save(issue);
        }
        return null;
    }


    @Override
    public String deleteRecord(Long id){
        try {
            issueRepository.deleteById(id);
            return "Record deleted successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Some error occurred during delete record";
        }
    }
}