package pack.springjpa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.springjpa1.data.dto.MessageDTO;
import pack.springjpa1.data.service.MessageService;

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


}
