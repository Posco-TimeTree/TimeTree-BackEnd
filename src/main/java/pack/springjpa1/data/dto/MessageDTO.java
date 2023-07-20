package pack.springjpa1.data.dto;


import lombok.*;
import pack.springjpa1.data.entity.MessageEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class MessageDTO {
    private Long messageId;
    private Long boxId;
    private String content;
    private Long userId;
    public void getUserId(Long id) {
        this.userId = id;
    }


}
