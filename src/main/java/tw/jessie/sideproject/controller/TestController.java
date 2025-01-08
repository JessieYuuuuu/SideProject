package tw.jessie.sideproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//用來測試的控制器
@Controller
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@GetMapping("/test2")
	public String test2() {
		return "fragments";
	}
}
