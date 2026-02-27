package vn.edu.hcmut.cse.adsoftweng.lab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private StudentRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.save(new Student("SV001", "Nguyen Van A", "nva@example.com", 20));
            repository.save(new Student("SV002", "Tran Thi B", "ttb@example.com", 15));
            repository.save(new Student("SV003", "Le Van C", "lvc@example.com", 19));
            repository.save(new Student("SV004", "Pham Thi D", "ptd@example.com", 22));
            repository.save(new Student("SV005", "Hoang Van E", "hve@example.com", 20));
            
            System.out.println("Da them 5 sinh vien mau vao PostgreSQL!");
        } else {
            System.out.println("Database da co du lieu, khong can khoi tao.");
        }
    }
}
