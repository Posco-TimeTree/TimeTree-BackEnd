package pack.springjpa1.data.entity;



import pack.springjpa1.data.dto.MessageDTO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Entity
@Builder
@Table(name = "message")
public class MessageEntity {
    @Id
    private Long messageId;
    private Long boxId;
    private String content;
    private Long userId;

    public MessageEntity() {}

    public MessageEntity(Long messageId, Long boxId, String content, Long userId) {
        this.messageId = messageId;
        this.boxId = boxId;
        this.content = content;
        this.userId = userId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public MessageDTO toDto() {
        return MessageDTO.builder()
                .messageId(messageId)
                .boxId(boxId)
                .content(content)
                .userId(userId)
                .build();
    }
}

