package ge.ibsu.demo.repositories;

import ge.ibsu.demo.dto.EmployeeSummary;
import ge.ibsu.demo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<EmployeeSummary> findAllProjectedBy();
}