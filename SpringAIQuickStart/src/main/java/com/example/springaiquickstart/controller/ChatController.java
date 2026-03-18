package com.example.springaiquickstart.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import reactor.core.publisher.Flux;

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


    /**
     *  流式输出
     * @param message
     * @return
     */
    @GetMapping(value = "/chatStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        Flux<ChatResponse> stream = deepSeekChatModel.stream(prompt);
        // SSE: 每个元素会作为一个 data: ... event 推送给前端
        return stream.map(resp -> resp.getResult().getOutput().getText());
    }
}
