package tw.jessie.sideproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.jessie.sideproject.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	// 模糊查詢 name 或 intro 符合 keyword
	@Query("SELECT o FROM yuOrder o WHERE o.name LIKE %:keyword% OR o.intro LIKE %:keyword%")
	List<Order> searchByKeyword(@Param("keyword") String keyword);
	
	// 抓取隨機專案
	@Query("SELECT o FROM yuOrder o ORDER BY FUNCTION('RAND')")
	List<Order> findRandomOrders();

	// 抓取指定id的專案資料
	@Query("SELECT o FROM yuOrder o WHERE o.orderid = :orderid")
	List<Order> findOrdersById(@Param("orderid") Long orderid);
}
