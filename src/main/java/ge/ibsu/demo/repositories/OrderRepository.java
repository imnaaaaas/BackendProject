package ge.ibsu.demo.repositories;
// quizz task 3
import ge.ibsu.demo.entities.Order;
import ge.ibsu.demo.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o " +
            "WHERE LOWER(o.customerName) LIKE LOWER(CONCAT('%', :customerName, '%')) " +
            "AND o.status = :status " +
            "ORDER BY o.createdAt DESC")
    List<Order> searchByCustomerNameAndStatus(
            @Param("customerName") String customerName,
            @Param("status") OrderStatus status
    );
}