package pack.springjpa1.data.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tree")
public class TreeEntity {
    @Id
    private Long userId;

    @Lob
    private byte[] imageData;

    public TreeEntity() {}

    public TreeEntity(Long userId, byte[] imageData) {
        this.userId = userId;
        this.imageData = imageData;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
