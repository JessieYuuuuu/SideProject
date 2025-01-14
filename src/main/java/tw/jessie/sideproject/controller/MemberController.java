package tw.jessie.sideproject.controller;

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
import tw.jessie.sideproject.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("/login")
	public String showLoginPage(Model model, HttpSession session) {
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("index目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
		} else {
			System.out.println("訪客模式:登入");
		}
		// 點擊登入button時返回 login.html 模板
		System.out.println("觸發/login");
		model.addAttribute("newMember", new Member());
		return "login";
	}

	@PostMapping("/login_submit") // 登入成功後加入session(記錄登入狀態)
	public String loginSubmit(@ModelAttribute Member member, BindingResult result, Model model, HttpSession session) {
		// 處理登入頁的登入表單
		if (result.hasErrors()) {
			return "login";
		}
		member = memberService.loginMember(member);
		if (member == null) {
			// 在資料庫內查無此會員，則返回登入頁
			model.addAttribute("loginError", true);
			return "login";
		} else {
			// 非上述即為登入成功，為此會員加入session
			session.setAttribute("member", member);
		}
		model.addAttribute("member", member);
		// 當登入成功後，將會員資料帶入model模板
		if (member.getAccount().equals("admin")) {
			model.addAttribute("tag", new Tag());
			return "loginadmin";
		} else {
			model.addAttribute("project", new Project());
			// 登入成功後，轉向頁面
			return "memberCenter";
		}
	}

	@RequestMapping("/addMember")
	public String addmember(Model model, HttpSession session) {
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("addMember目前登入狀態:" + member.getName());
			model.addAttribute("member", member);
		} else {
			System.out.println("訪客模式:加入會員");
		}
		model.addAttribute("newMember", new Member());
		return "addMember";
	}

	@PostMapping("/addMember_sub") // 用這個方式將接收的參數轉為model物件,綁定狀態如何,用model處理下一個畫面
	public String addMemberSub(@Valid @ModelAttribute Member member, BindingResult result, Model model) {
		// 處理從註冊頁註冊的資料，註冊失敗，轉回註冊頁 | 註冊成功，轉向登入頁
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors().toString());
			return "addMember";
		}
		memberService.addMember(member);
		System.out.println(member.getAccount());
		System.out.println(member.getPasswd());
		System.out.println(member.getName());
		return "login";
	}

	@GetMapping("/userloging")
	public String userLoging(Model model, HttpSession session) {
		System.out.println("userlogingdebug:已進入userloging");
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("userloging目前登入狀態:" + member.getName());
			model.addAttribute("member", member);
			System.out.println("userlogingdebug1:" + member.getAccount());
			if (member.getAccount().equals("admin")) {
				model.addAttribute("tag", new Tag());
				return "loginadmin";
			} else {
				model.addAttribute("project", new Project());
				// 登入成功後，轉向頁面
				return "memberCenter";
			}
		} else {
			Member member = new Member();
			model.addAttribute("登入失敗");
			return "login";
		}
	}

	@PostMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "index";
	}
}
