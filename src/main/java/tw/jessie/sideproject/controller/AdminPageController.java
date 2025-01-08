package tw.jessie.sideproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import tw.jessie.sideproject.model.Member;
import tw.jessie.sideproject.model.Tag;
import tw.jessie.sideproject.repository.TagRepository;

@Controller
public class AdminPageController {

	@Autowired
	private TagRepository tagRepository;

	@PostMapping("/addtag")
	public String addTag(@ModelAttribute Tag tag, Model model, HttpSession session) {

		tag.getTagname();
		Long tagSave = tagRepository.save(tag).getTagid();
		System.out.println(tagSave);
//		tagRepository.save(tag);
		if (tagSave != 0) {
			model.addAttribute("tagmegs", "新增成功");
		} else {
			model.addAttribute("tagmegs", "新增失敗");
		}
		if (session.getAttribute("member") != null) {
			Member member = (Member) session.getAttribute("member");
			System.out.println("目前登入狀態:" + member.getName() + "%n");
			model.addAttribute("member", member);
			return "loginadmin";
		} else {
			System.out.println("XX");
			return "login";
		}

	}

}
