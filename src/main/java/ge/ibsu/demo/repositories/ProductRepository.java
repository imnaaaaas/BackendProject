package ge.ibsu.demo.repositories;
// quizz task 6
import ge.ibsu.demo.dto.ProductStats;
import ge.ibsu.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new ge.ibsu.demo.dto.ProductStats(p.category, AVG(p.price)) " +
            "FROM Product p " +
            "GROUP BY p.category")
    List<ProductStats> findAveragePriceByCategory();
}