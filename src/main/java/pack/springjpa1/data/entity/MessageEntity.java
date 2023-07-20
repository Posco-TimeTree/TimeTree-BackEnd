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

    @PrePersist
    private void prePersist() {
        if (boxId == null) {
            // Auto-increment boxId based on userId
            // Find the maximum boxId for the given userId and increment by 1
            EntityManager entityManager = Persistence.createEntityManagerFactory("persistence-unit-name").createEntityManager();
            Query query = entityManager.createQuery("SELECT MAX(m.boxId) FROM MessageEntity m WHERE m.userId = :userId");
            query.setParameter("userId", userId);
            Long maxBoxId = (Long) query.getSingleResult();
            if (maxBoxId == null) {
                maxBoxId = 0L;
            }
            boxId = maxBoxId + 1;
        }
    }
}