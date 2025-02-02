package tw.jessie.sideproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import tw.jessie.sideproject.model.Keywords;
import tw.jessie.sideproject.model.Member;
import tw.jessie.sideproject.model.Order;
import tw.jessie.sideproject.service.KeywordService;
import tw.jessie.sideproject.service.MemberService;
import tw.jessie.sideproject.service.OrderService;

@Controller
public class PageController {

	@Autowired
	private KeywordService keywordService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberService memberService;

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
//		抓取隨機會員資料
		List<Member> ranMembers = memberService.getRandomMembers();
		for (Member member : ranMembers) {
			if (member.getPicurl() == null || member.getPicurl() == "") {
				member.setPicurl("../img/memberImg.jpg");
				System.out.println(member.getMemberid());
			}
		}
		model.addAttribute("ranMembers", ranMembers);
//		抓取熱門申請專案
		List<Object[]> owentLise = orderService.getWantedCountByOrderId();
		List<Order> wentOrders = new ArrayList<>();
		List<Long> wantCounts = new ArrayList<>();
		int caseCouunt = 0;
		for (Object[] lists : owentLise) {
			if (caseCouunt >= 6) {
				break;
			}
			Order order = (Order) lists[0];
			Long want = (Long) lists[1];
			System.out.println("訂單: " + order.getName() + ", 申請人數: " + lists[1] + ",截止日期:" + order.getDeadline());
			wentOrders.add(order);
			wantCounts.add(want);
			caseCouunt++;
		}
		model.addAttribute("keywordCase", wentOrders);
		model.addAttribute("wantCounts", wantCounts);
//		抓取隨機關鍵字
		List<Keywords> list = keywordService.getKeywordDesc();
		model.addAttribute("kw", list);
		return "index";
	}

	@GetMapping("/search")
	public String search(Model model, HttpSession session) {
		System.out.println("---search---");
//		判斷是否有登入狀態
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("search目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
		} else {
			System.out.println("訪客模式:搜尋頁");
		}
//		抓取隨機關鍵字
		List<Keywords> list = keywordService.getKeywordDesc();
		model.addAttribute("kw", list);

//		從session拿取搜尋邏輯的結果
		@SuppressWarnings("unchecked")
		List<Order> keywordCase = (List<Order>) session.getAttribute("keywordCase");
		if (keywordCase != null) {
			System.out.println("test :" + keywordCase);
			for (Order order : keywordCase) {
				// 如果訂單沒圖片，加入圖片
				if (order.getPicurl() == null) {
					order.setPicurl("../img/caseImg.jpg");
				}
			}
			model.addAttribute("keywordCase", keywordCase);
		} else {
			System.out.println("注入失敗");
		}
		session.setAttribute("keywordCase", keywordCase);
		return "search";
	}

//	找case進入
	@GetMapping("/searchCase")
	public String searchCase(Model model, HttpSession session) {
		System.out.println("---進入searchCase---");
		// 判斷是否有登入狀態
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("searchCase目前登入狀態:" + member.getName());
			model.addAttribute("member", member);
		} else {
			System.out.println("searchCase訪客模式");
		}

//		抓取隨機關鍵字
		List<Keywords> kwlist = keywordService.getKeywordDesc();
		model.addAttribute("kw", kwlist);

//		抓取隨機專案
		List<Order> ranOrders = orderService.getRandomOrders();
		for (Order order : ranOrders) {
			if (order.getPicurl() == null) {
				order.setPicurl("../img/caseImg.jpg");
			}
		}
		model.addAttribute("keywordCase", ranOrders);
		session.setAttribute("keywordCase", ranOrders);
		return "search";
	}

}
