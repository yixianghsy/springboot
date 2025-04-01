package hsy.com.mongodb.vo;

import lombok.*;
import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User implements Serializable {

    @Id
    private Long id;

    private String username;

    private String nickname;

}
