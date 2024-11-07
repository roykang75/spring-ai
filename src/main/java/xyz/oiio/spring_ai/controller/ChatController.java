package xyz.oiio.spring_ai.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
//@RequestMapping("/api")
@RestController
public class ChatController {

    private final ChatClient chatClient;

//    public ChatController(ChatClient chatClient) {
//        this.chatClient = chatClient;
//    }

    @GetMapping("/ai/simple")
    public Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("completion", this.chatClient.prompt().user(message).call().content());
    }

    @GetMapping("/ai/simple/flux")
    public Flux<String> flux(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Flux<String> output = chatClient.prompt()
            .user("Tell me a joke")
            .stream()
            .content();

        return output;
    }
}
