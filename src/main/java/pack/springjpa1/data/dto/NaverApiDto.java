package pack.springjpa1.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaverApiDto {
    String naverId;
    String name;
    String email;
}
