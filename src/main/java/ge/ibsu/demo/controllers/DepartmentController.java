package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.DepartmentDTO;
import ge.ibsu.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getDepartments(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city) {

        return ResponseEntity.ok(departmentService.getDepartments(country, city));
    }
}