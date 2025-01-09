package tw.jessie.sideproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.jessie.sideproject.service.KeywordService;

@Controller
public class KeywordController {
	
	@Autowired
	private KeywordService keywordService;
	
	@PostMapping("/saveKeyword")
	public ResponseEntity<String> saveKeyword(@RequestParam String userKeyword){
		keywordService.saveKeyword(userKeyword);
		return ResponseEntity.ok("新增成功");
	}

}
