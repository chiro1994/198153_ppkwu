package com.example.rev;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rev")
public class RevController {

	@RequestMapping("/{word}")
    public String mainPage(@PathVariable("word") String word) {
		StringBuffer sb = new StringBuffer(word);
        return sb.reverse().toString();
    }
}
