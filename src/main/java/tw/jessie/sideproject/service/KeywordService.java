package tw.jessie.sideproject.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.jessie.sideproject.model.Keywords;
import tw.jessie.sideproject.model.Order;
import tw.jessie.sideproject.repository.KeywordRepository;
import tw.jessie.sideproject.repository.OrderRepository;

@Service
public class KeywordService {

	@Autowired
	private KeywordRepository keywordRepository;

	@Autowired
	private OrderRepository orderRepository;

//	儲存關鍵字
	public void saveKeyword(String userKeyword) {
		Optional<Keywords> SQLKeyword = keywordRepository.findByKeyword(userKeyword);
		if (SQLKeyword.isPresent()) {
			Keywords keyword = SQLKeyword.get();
			keyword.setTimes(keyword.getTimes() + 1);
			keywordRepository.save(keyword);
			System.out.println("關鍵詞 " + keyword.getKeyword() + " 搜尋次數+1");
		} else {
			Keywords newKeyword = new Keywords();
			newKeyword.setKeyword(userKeyword);
			newKeyword.setTimes(1);
			keywordRepository.save(newKeyword);
			System.out.println("新增 " + newKeyword.getKeyword() + " 關鍵詞");
		}
	}

//	回傳搜尋次數最高前5名
	public List<Keywords> getKeywordDesc() {
		List<Keywords> top5Keywords = keywordRepository.findTop5ByOrderByTimesDesc();
		return top5Keywords;
	}

//	抓取隨機關鍵字
	public String getRandomKeyword() {
		Keywords keywords = keywordRepository.findRandomKeyword();
		return keywords != null ? keywords.getKeyword() : "目前無其他關鍵字";
	}

//	利用關鍵字查詢，將查詢結果存在List(目前關鍵字搜尋的邏輯)
	public List<Order> searchKeywords(String keyword) {
		return orderRepository.searchByKeyword(keyword);
	}

//	利用關鍵字查詢，將查詢結果存在List，並將list內容轉為JSON(沒用到，暫時保留)
	public String searchKeywordsJSON(String keyword) {
		List<Order> list = orderRepository.searchByKeyword(keyword);
		String jsonResult = new String();
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonResult = mapper.writeValueAsString(list);
			System.out.println(jsonResult);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResult;
	}

//	將搜尋結果按時間排序
	public void timeSortASC(List<Order> keyOrder) {
		if (keyOrder != null) {
			keyOrder.sort(Comparator.comparing(Order::getDeadline).reversed());
		} else {
			System.out.println("訂單注入失敗");
		}
	}
//	將搜尋結果按時間排序
	public void timeSortDSC(List<Order> keyOrder) {
		if (keyOrder != null) {
			keyOrder.sort(Comparator.comparing(Order::getDeadline));
		} else {
			System.out.println("訂單注入失敗");
		}
	}
//	將搜尋結果按收藏數排序
	public void getCollectedSort(List<Order> keyOrder) {
		if (keyOrder != null && !keyOrder.isEmpty()) {
			keyOrder.sort(Comparator.comparing(Order::getCollectCount).reversed());
		} else {
			System.out.println("訂單注入失敗");
		}
	}
}
