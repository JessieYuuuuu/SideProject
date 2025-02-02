package tw.jessie.sideproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.jessie.sideproject.model.Order;
import tw.jessie.sideproject.repository.MemberOrderRepository;
import tw.jessie.sideproject.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private MemberOrderRepository memberOrderRepository;

//	抓取隨機5個專案
	public List<Order> getRandomOrders() {
		List<Order> allOrders = orderRepository.findRandomOrders();
		// 只取前5個
		return allOrders.stream().limit(5).collect(Collectors.toList());
	}

	public List<Order> getAllOrders() {
		return orderRepository.findRandomOrders();
	}

	// 抓取所有專案申請人數
	public List<Object[]> getWantedCountByOrderId() {
		return memberOrderRepository.findWantedCountByOrderId();
	}

	// 抓取所有專案收藏人數
//	public List<Object[]> getAllcaseCollectedCount() {
//		return memberOrderRepository.findCollectedCountByOrderId();
//	}
	// 抓取指定專案收藏人數
	public Long getCollectedCountByOrderId(Long orderId) {
		List<Object[]> result = memberOrderRepository.findCollectedCountByOrderId(orderId);
		if (result.isEmpty()) {
			return 0L;
		}
		return (Long) result.get(0)[0];
	}

	public List<Order> getOrderById(Long orderid) {
		return orderRepository.findOrdersById(orderid);
	}

}
