package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;
    
    // 1. GET /api/students - Lấy tất cả sinh viên
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = service.getAll();
        return ResponseEntity.ok(students);
    }
    
    // 2. GET /api/students/{id} - Lấy sinh viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = service.getById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    
    // 3. GET /api/students/search?keyword= - Tìm kiếm sinh viên
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam(required = false) String keyword) {
        List<Student> students = service.searchByName(keyword);
        return ResponseEntity.ok(students);
    }
    
    // 4. POST /api/students - Tạo sinh viên mới
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (service.existsById(student.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        Student savedStudent = service.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
    
    // 5. PUT /api/students/{id} - Cập nhật sinh viên
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        student.setId(id);
        Student updatedStudent = service.save(student);
        return ResponseEntity.ok(updatedStudent);
    }
    
    // 6. DELETE /api/students/{id} - Xóa sinh viên
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}