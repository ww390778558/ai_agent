package com.example.springaiquickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.deepseek.DeepSeekChatModel;

/**
 * @Author ww
 * @PackageName ai_agent
 * @Package com.example.springaiquickstart.controller
 * @Version 1.0
 */
@RestController
@RequestMapping("/ai")
public class ChatController {
    @Autowired
    private DeepSeekChatModel deepSeekChatModel;

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message",defaultValue = "你是谁？") String message){
        String resp = deepSeekChatModel.call(message);
        return resp;
    }

}
