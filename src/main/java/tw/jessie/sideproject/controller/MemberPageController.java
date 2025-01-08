package tw.jessie.sideproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberPageController {

	@PostMapping("/addproject")
	public void addproject(Model model, HttpSession session) {

	}

}
