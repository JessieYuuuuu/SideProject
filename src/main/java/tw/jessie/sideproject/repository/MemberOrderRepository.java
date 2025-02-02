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
	@Query("SELECT yo, COALESCE(COUNT(mo.wanted), 0) AS wanted_count " + "FROM yuOrder yo LEFT JOIN yo.memberOrders mo "
			+ "ON yo.orderid = mo.id.orderid AND mo.wanted = true " + "WHERE yo.deadline>CURRENT_DATE " + "GROUP BY yo "
			+ "ORDER BY wanted_count DESC")
	List<Object[]> findWantedCountByOrderId();

	// 統計每個專案的收藏人數
	@Query("SELECT yo, COALESCE(COUNT(mo.wanted), 0) AS collected_count "
			+ "FROM yuOrder yo LEFT JOIN yo.memberOrders mo " + "ON yo.orderid = mo.id.orderid AND mo.collected = true "
			+ "WHERE yo.deadline>CURRENT_DATE " + "GROUP BY yo " + "ORDER BY collected_count DESC")
	List<Object[]> findCollectedCountByOrder();

	// 查詢指定專案id的收藏人數
	@Query("SELECT COALESCE(COUNT(mo.wanted), 0) AS collected_count " + "FROM yuOrder yo LEFT JOIN yo.memberOrders mo "
			+ "ON yo.orderid = mo.id.orderid AND mo.collected = true "
			+ "WHERE yo.deadline > CURRENT_DATE AND yo.orderid = :orderId " + "GROUP BY yo")
	List<Object[]> findCollectedCountByOrderId(Long orderId);

}