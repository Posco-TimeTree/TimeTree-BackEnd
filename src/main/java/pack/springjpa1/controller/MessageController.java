package pack.springjpa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.springjpa1.data.dto.MessageDTO;
import pack.springjpa1.data.entity.MessageEntity;
import pack.springjpa1.data.service.MessageService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000" ,allowCredentials = "true")
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
        System.out.println("User ID: " + userId); // Logging statement to check the received value

        List<MessageEntity> messages = messageService.getMessagesByUserId(userId);

        // Logging the contents of the list
        System.out.println("Messages: ");
        for (MessageEntity message : messages) {
            System.out.println("Message ID: " + message.getMessageId());
            System.out.println("Box ID: " + message.getBoxId());
            System.out.println("Content: " + message.getContent());
            System.out.println("User ID: " + message.getUserId());
            System.out.println("-----------------------------------");
        }

        return ResponseEntity.ok(messages);
    }

}
