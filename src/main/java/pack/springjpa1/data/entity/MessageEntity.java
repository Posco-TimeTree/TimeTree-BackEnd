package pack.springjpa1.data.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment 설정
    private Long messageId;

    private Long boxId;

    private String content;

    private Long userId;

    public MessageEntity() {}

    public MessageEntity(Long boxId, String content, Long userId) {
        this.boxId = boxId;
        this.content = content;
        this.userId = userId;
    }

    // Getters and setters



    /*

     */
}