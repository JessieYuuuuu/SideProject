package tw.jessie.sideproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import tw.jessie.sideproject.model.Keywords;
import tw.jessie.sideproject.model.Member;
import tw.jessie.sideproject.service.KeywordService;

@Controller
public class PageController {

	@Autowired
	private KeywordService keywordService;
	
	@GetMapping("/index")
	public String index(Model model, HttpSession session) {
		System.out.println("---home---");
		// 判斷是否有登入狀態
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("index目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
		} else {
			System.out.println("訪客模式:首頁");
		}
		// 抓取隨機關鍵字
		List<Keywords> list = keywordService.getKeywordDesc();
		model.addAttribute("kw",list);
		return "index";
	}

	@GetMapping("/search")
	public String search(Model model, HttpSession session) {
		System.out.println("---search---");
		// 判斷是否有登入狀態
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("search目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
		} else {
			System.out.println("訪客模式:搜尋頁");
		}
		// 抓取隨機關鍵字
		List<Keywords> list = keywordService.getKeywordDesc();
		model.addAttribute("kw",list);
		return "search";
	}


}
