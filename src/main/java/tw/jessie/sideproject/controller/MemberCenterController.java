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
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			model.addAttribute("member",member);
			return"memberCenter";
			}else {
				return"forward:/index";
			}
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
		return"memberLike";
	}
	@GetMapping("/memberMail")
	public String memberMail() {
		return"memberMail";
	}
}
