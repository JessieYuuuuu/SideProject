package tw.jessie.sideproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import tw.jessie.sideproject.model.Member;

//會員中心控制頁面
@Controller
public class MemberCenterController {

	@GetMapping("/membercenter")
	public String memberCenter(Model model, HttpSession session){
		Member member = new Member();
		member.setAccount("Test");
		member.setName("測試");
		model.addAttribute("member", member);
		session.getAttribute("member");
		return"memberCenter";
	}
	
	@GetMapping("/memberSources")
	public String memberSources() {
		return"memberSources";
	}
	
	@GetMapping("/memberWork")
	public String memberWork() {
		return"memberWork";
	}
	@GetMapping("/memberPush")
	public String memberPush() {
		return"memberPush";
	}
	@GetMapping("/memberPull")
	public String memberPull() {
		return"memberPull";
	}
	@GetMapping("/memberLike")
	public String memberLike() {
		return"memberPush";
	}
	@GetMapping("/memberMail")
	public String memberMail() {
		return"memberPush";
	}
}
