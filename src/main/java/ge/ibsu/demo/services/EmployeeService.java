package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddEmployee;
import ge.ibsu.demo.entities.Department;
import ge.ibsu.demo.entities.Employee;
import ge.ibsu.demo.repositories.EmployeeRepository;
import ge.ibsu.demo.utils.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(() -> new Exception("EMPLOYEE_NOT_FOUND"));
    }

    @Transactional
    public Employee saveEmployee(AddEmployee data, Long id) throws Exception {
        GeneralUtil.checkRequiredProperties(data, Arrays.asList("firstName", "lastName", "phone", "salary"));
        Employee employee = new Employee();

        if (id != null) {
            employee = getById(id);
        }

        employee.setFirstName(data.getFirstName());
        employee.setLastName(data.getLastName());
        employee.setEmail(data.getEmail());
        employee.setPhone(data.getPhone());
        employee.setSalary(data.getSalary());
        employee.setHireDate(new Date());

        Department department = this.departmentService.getById(data.getDepartmentId());

        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }
}
