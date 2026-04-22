package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.DepartmentDTO;
import ge.ibsu.demo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getDepartments(String country, String city) {
        return departmentRepository.findAllWithDetails(country, city);
    }
}