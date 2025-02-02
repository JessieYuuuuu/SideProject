package tw.jessie.sideproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import tw.jessie.sideproject.model.Member;
import tw.jessie.sideproject.service.MemberService;

@Controller
public class MemberPageController {

	@Autowired
	private MemberService memberService;

	@PostMapping("/addproject")
	public void addproject(Model model, HttpSession session) {

	}

//	會員公開資料展示頁
	@PostMapping("/memberShow")
	public String memberShow(@RequestParam Long memberid, Model model) {
		System.out.println("前端傳送的會員ID : " + memberid);
		List<Member> memberShow = memberService.getMemberById(memberid);
		for (Member memberCheck : memberShow) {
			System.out.println(memberCheck.getName());
			// 如果訂單沒圖片，加入圖片
			if (memberCheck.getPicurl() == null) {
				memberCheck.setPicurl("../img/caseImg.jpg");
			}
		}
		model.addAttribute("memberShow", memberShow);
		return "memberShow";
	}

}
