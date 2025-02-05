package hsy.com.redis.controller;



import hsy.com.redis.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UserController {


	@GetMapping(value="user")
	public Object account(){
		
		return JsonData.buildSuccess("www.xdclass.net");
		
	}
	
	
	
}
