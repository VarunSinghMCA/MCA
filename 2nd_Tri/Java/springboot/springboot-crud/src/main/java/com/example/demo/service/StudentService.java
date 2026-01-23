package com.example.demo.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
@Service
public class StudentService {
 private final StudentRepository repo;
 public StudentService(StudentRepository repo){this.repo=repo;}
 public Student save(Student s){return repo.save(s);}
 public List<Student> getAll(){return repo.findAll();}
 public Student getById(Long id){return repo.findById(id).orElse(null);}
 public Student update(Student s){return repo.save(s);}
 public String delete(Long id){repo.deleteById(id);return "Deleted";}
}
