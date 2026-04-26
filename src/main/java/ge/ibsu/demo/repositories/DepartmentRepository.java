package ge.ibsu.demo.repositories;
// quizz task 4
import ge.ibsu.demo.dto.DepartmentDTO;
import ge.ibsu.demo.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("""
        SELECT new ge.ibsu.demo.dto.DepartmentDTO(
            d.name,
            CONCAT(e.firstName, ' ', e.lastName),
            c.name,
            l.city,
            l.streetAddress
        )
        FROM Department d
        LEFT JOIN d.manager e
        LEFT JOIN d.location l
        LEFT JOIN l.country c
        WHERE (:country IS NULL OR LOWER(c.name) = LOWER(:country))
          AND (:city IS NULL OR LOWER(l.city) = LOWER(:city))
    """)
    List<DepartmentDTO> findAllWithDetails(
            @Param("country") String country,
            @Param("city") String city
    );

    @Query("SELECT DISTINCT d FROM Department d JOIN FETCH d.employees")
    List<Department> findAllWithEmployees();
}