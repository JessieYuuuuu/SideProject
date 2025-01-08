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
import tw.jessie.sideproject.model.Member;
import tw.jessie.sideproject.model.Project;
import tw.jessie.sideproject.model.Tag;
import tw.jessie.sideproject.repository.MemberRepository;
import tw.jessie.sideproject.repository.TagRepository;
import tw.jessie.sideproject.service.MemberService;

@Controller
public class PageController {

	@GetMapping("/search")
	public String search(Model model, HttpSession session) {
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("index目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
		} else {
			System.out.println("indexXX");
		}
		return "search";
	}

	@GetMapping("/index")
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("index目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
		} else {
			System.out.println("indexXX");
		}
		return "index";
	}
}
