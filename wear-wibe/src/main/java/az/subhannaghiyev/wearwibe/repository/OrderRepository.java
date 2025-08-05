package az.subhannaghiyev.wearwibe.repository;

import az.subhannaghiyev.wearwibe.entity.Order;
import az.subhannaghiyev.wearwibe.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status")
    List<Object[]> countGroupedByStatus();

    List<Order> findByItemsProductId(Long productId);

    @Query("SELECT SUM(oi.order.totalPrice * oi.quantity) FROM Order o JOIN o.items oi WHERE o.user.id = :userId")
    BigDecimal sumTotalByUserId(@Param("userId") Long userId);

    @Query("SELECT oi.product, SUM(oi.quantity) as totalQuantity " +
            "FROM Order o JOIN o.items oi " +
            "GROUP BY oi.product " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findTopProducts(Pageable pageable);

    default List<Product> findTopProducts(int topN) {
        return findTopProducts(PageRequest.of(0, topN))
                .stream()
                .map(row -> (Product) row[0])
                .collect(Collectors.toList());
    }

    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.items")
    List<Order> findAllWithItems();

}
