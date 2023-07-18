package pack.springjpa1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.springjpa1.data.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, String> {
    MessageEntity findByUserIdAndBoxId(Long userId,Long boxId);
}
