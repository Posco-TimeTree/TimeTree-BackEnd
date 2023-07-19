package pack.springjpa1.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class TreeDTO {
    private Long userId;
    private byte[] imageData;
}

