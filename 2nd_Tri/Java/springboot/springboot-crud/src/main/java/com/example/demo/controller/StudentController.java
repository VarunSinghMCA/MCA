package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
@RestController
@RequestMapping("/students")
public class StudentController {
 private final StudentService service;
 public StudentController(StudentService service){this.service=service;}
 @PostMapping public Student add(@RequestBody Student s){return service.save(s);}
 @GetMapping public List<Student> list(){return service.getAll();}
 @GetMapping("/{id}") public Student get(@PathVariable Long id){return service.getById(id);}
 @PutMapping("/{id}") public Student update(@PathVariable Long id,@RequestBody Student s){
  s.setId(id); return service.update(s);
 }
 @DeleteMapping("/{id}") public String delete(@PathVariable Long id){return service.delete(id);}
}
