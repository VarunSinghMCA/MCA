package com.example.demo.model;
import jakarta.persistence.*;
@Entity
public class Student {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private String name;
 private String department;
 private int age;
 public Student(){}
 public Student(String name,String department,int age){
  this.name=name;this.department=department;this.age=age;
 }
 public Long getId(){return id;}
 public void setId(Long id){this.id=id;}
 public String getName(){return name;}
 public void setName(String n){this.name=n;}
 public String getDepartment(){return department;}
 public void setDepartment(String d){this.department=d;}
 public int getAge(){return age;}
 public void setAge(int a){this.age=a;}
}
