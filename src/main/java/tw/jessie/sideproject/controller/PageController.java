package tw.jessie.sideproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import tw.jessie.sideproject.model.Keywords;
import tw.jessie.sideproject.model.Member;
import tw.jessie.sideproject.model.Project;
import tw.jessie.sideproject.model.Tag;
import tw.jessie.sideproject.repository.MemberRepository;
import tw.jessie.sideproject.repository.TagRepository;
import tw.jessie.sideproject.service.KeywordService;
import tw.jessie.sideproject.service.MemberService;

@Controller
public class PageController {

	@Autowired
	private KeywordService keywordService;

	@GetMapping("/search")
	public String search(Model model, HttpSession session) {
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("index目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
		} else {
			System.out.println("訪客模式:搜尋頁");
		}
		List<Keywords> list = keywordService.getKeywordDesc();
		for(Keywords keywords : list) {
			System.out.println(keywords.getKeyword());
		}
		model.addAttribute("kw",list);
		return "search";
	}

	@GetMapping("/index")
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("index目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
		} else {
			System.out.println("訪客模式:首頁");
		}
		List<Keywords> list = keywordService.getKeywordDesc();
		for(Keywords keywords : list) {
			System.out.println(keywords.getKeyword());
		}
		model.addAttribute("kw",list);
		return "index";
	}
}
