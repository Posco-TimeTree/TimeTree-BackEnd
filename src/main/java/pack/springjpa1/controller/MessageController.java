package pack.springjpa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pack.springjpa1.data.dto.MessageDTO;
import pack.springjpa1.data.service.MessageService;

@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public ResponseEntity<String> createMessage(@RequestBody MessageDTO messageDTO) {
        messageService.createMessage(messageDTO);
        return new ResponseEntity<>("Message created successfully", HttpStatus.CREATED);
    }
}
