package tw.jessie.sideproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.jessie.sideproject.model.Keywords;

@Repository
public interface KeywordRepository extends JpaRepository<Keywords, Long>{

	Optional<Keywords>  findByKeyword(String keyword); //抓取用戶輸入的關鍵詞
	
	List<Keywords> findTop5ByOrderByTimesDesc(); //抓取回傳資料的前5筆
}
