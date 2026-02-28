package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;
    
    // 1. Trang danh sách + tìm kiếm
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword);
        return "students/list";
    }

    // 2. Trang chi tiết sinh viên (ĐÃ FIX LỖI 500 TẠI ĐÂY)
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable("id") String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students?error=notfound";
        }
        model.addAttribute("student", student);
        return "students/detail";
    }

    // 3. Trang form thêm mới (GET)
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "students/form";
    }

    // 4. Xử lý thêm mới (POST)
    @PostMapping("/new")
    public String createStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        // Lưu ý: Vì Entity của bạn quy định ID là String[cite: 255], 
        // nếu bạn không tự nhập ID trên Form, bạn cần tự sinh ID ở đây.
        if (student.getId() == null || student.getId().isEmpty()) {
             student.setId(java.util.UUID.randomUUID().toString());
        }
        
        service.save(student);
        redirectAttributes.addFlashAttribute("message", "Thêm sinh viên thành công!");
        return "redirect:/students";
    }

    // 5. Trang form chỉnh sửa (GET) (ĐÃ FIX LỖI 500 TẠI ĐÂY)
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students?error=notfound";
        }
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        return "students/form";
    }

    // 6. Xử lý cập nhật (POST) (ĐÃ FIX LỖI 500 TẠI ĐÂY)
    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable("id") String id, 
                                @ModelAttribute Student student,
                                RedirectAttributes redirectAttributes) {
        student.setId(id);
        service.save(student);
        redirectAttributes.addFlashAttribute("message", "Cập nhật sinh viên thành công!");
        return "redirect:/students/" + id;
    }

    // 7. Xử lý xóa (POST) (ĐÃ FIX LỖI 500 TẠI ĐÂY)
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        if (!service.existsById(id)) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên!");
            return "redirect:/students";
        }
        service.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa sinh viên thành công!");
        return "redirect:/students";
    }
}