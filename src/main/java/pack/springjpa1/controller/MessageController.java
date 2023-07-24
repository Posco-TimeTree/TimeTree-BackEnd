package pack.springjpa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.springjpa1.data.dto.MessageDTO;
import pack.springjpa1.data.entity.MessageEntity;
import pack.springjpa1.data.service.MessageService;

import java.util.List;

//@CrossOrigin(origins = "http://175.45.202.192:3000" ,allowCredentials = "true")
//@CrossOrigin(origins = "*", allowCredentials = "true")
//@CrossOrigin(origins = {"http://175.45.202.192:3000"},
//        allowedOriginPatterns = "*",
//        allowCredentials = true)
@CrossOrigin(origins = {"https://verdant-creponne-dc7564.netlify.app/", "http://localhost:3000"} ,allowCredentials = "true")

@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public String showMessage(@RequestBody MessageDTO messageDTO) {
        String content = messageDTO.getContent();
        Long userId = messageDTO.getUserId();


        messageService.createMessage(messageDTO);
        return "Message received successfully";
    }
    @GetMapping("/messages/{userId}")
    public ResponseEntity<List<MessageEntity>> getMessagesByUserId(@PathVariable Long userId) {

        List<MessageEntity> messages = messageService.getMessagesByUserId(userId);




        return ResponseEntity.ok(messages);
    }

}
