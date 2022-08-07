package com.simplize.springboot.consumingrest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumingController {

	// http://localhost:8080/github?user=ntdat104
	@GetMapping("/github")
	public ResponseEntity<Object> getGithubUser(@RequestParam(value = "user", defaultValue = "ntdat104") String user) throws Exception {
		try {
			// String url = "https://api.github.com/users/{user}";
			String url = String.format("https://api.github.com/users/%s", user);
			RestTemplate restTemplate = new RestTemplate();
			Object result = restTemplate.getForObject(url, Object.class);
			Object response = new Response(200, "success", result);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Object response = new Response(200, "success", null);
			return ResponseEntity.ok(response);
		}
	}
}
