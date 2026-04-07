package ge.ibsu.demo.services;

import ge.ibsu.demo.entities.Department;
import ge.ibsu.demo.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id) throws Exception {
        return  departmentRepository.findById(id).orElseThrow(() -> new Exception("DEPARTMENT_NOT_FOUND"));
    }
}
