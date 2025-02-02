package tw.jessie.sideproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.jessie.sideproject.model.MemberOrder;
import tw.jessie.sideproject.model.MemberOrderKey;

@Repository
public interface MemberOrderRepository extends JpaRepository<MemberOrder, MemberOrderKey> {

	// 統計每個專案的申請人數
	@Query("SELECT o, COALESCE(COUNT(mo.wanted), 0) AS wanted_count " + "FROM Order o LEFT JOIN o.memberOrders mo "
			+ "ON o.orderid = mo.id.orderid AND mo.wanted = true " + "WHERE o.deadline>CURRENT_DATE " + "GROUP BY o "
			+ "ORDER BY wanted_count DESC")
	List<Object[]> findWantedCountByOrderId();

	// 統計每個專案的收藏人數
	@Query("SELECT o, COALESCE(COUNT(mo.wanted), 0) AS collected_count "
			+ "FROM Order o LEFT JOIN o.memberOrders mo " + "ON o.orderid = mo.id.orderid AND mo.collected = true "
			+ "WHERE o.deadline>CURRENT_DATE " + "GROUP BY o " + "ORDER BY collected_count DESC")
	List<Object[]> findCollectedCountByOrder();

	// 查詢指定專案id的收藏人數
	@Query("SELECT COALESCE(COUNT(mo.wanted), 0) AS collected_count " + "FROM Order o LEFT JOIN o.memberOrders mo "
			+ "ON o.orderid = mo.id.orderid AND mo.collected = true "
			+ "WHERE o.deadline > CURRENT_DATE AND o.orderid = :orderId " + "GROUP BY o")
	List<Object[]> findCollectedCountByOrderId(Long orderId);

}