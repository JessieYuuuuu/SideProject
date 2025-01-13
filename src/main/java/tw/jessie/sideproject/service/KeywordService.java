package tw.jessie.sideproject.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.jessie.sideproject.model.Keywords;
import tw.jessie.sideproject.repository.KeywordRepository;

@Service
public class KeywordService {
	
	@Autowired
	private KeywordRepository keywordRepository;
	
//	儲存關鍵字
	public void saveKeyword(String userKeyword) {
		Optional<Keywords> SQLKeyword = keywordRepository.findByKeyword(userKeyword);
		if(SQLKeyword.isPresent()) {
			Keywords keyword = SQLKeyword.get();
			keyword.setTimes(keyword.getTimes()+1);
			keywordRepository.save(keyword);
			System.out.println("關鍵詞 "+keyword.getKeyword()+" 搜尋次數+1");
		}else {
			Keywords newKeyword = new Keywords();
			newKeyword.setKeyword(userKeyword);
			newKeyword.setTimes(1);
			keywordRepository.save(newKeyword);
			System.out.println("新增 "+newKeyword.getKeyword()+" 關鍵詞");
		}
	}
	
//	回傳搜尋次數最高前5名
	public List<Keywords> getKeywordDesc(){
		List<Keywords> top5Keywords = keywordRepository.findTop5ByOrderByTimesDesc();
		return top5Keywords;
	}
	
//	隨機關鍵字相關處理
	public String getRandomKeyword() {
		Keywords keywords = keywordRepository.findRandomKeyword();
		return keywords != null ? keywords.getKeyword() : "目前無其他關鍵字";
	}
}
