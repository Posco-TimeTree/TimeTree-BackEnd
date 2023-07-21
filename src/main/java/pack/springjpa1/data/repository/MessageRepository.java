package pack.springjpa1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pack.springjpa1.data.entity.MessageEntity;

import java.util.List;
public interface MessageRepository extends JpaRepository<MessageEntity, String> {
    MessageEntity findByUserIdAndBoxId(Long userId,Long boxId);
    @Query("SELECT MAX(m.boxId) FROM MessageEntity m WHERE m.userId = :userId")
    Long findMaxBoxIdByUserId(@Param("userId") Long userId);

    @Query("SELECT m FROM MessageEntity m WHERE m.userId = :userId")
    List<MessageEntity> findByUserId(@Param("userId") Long userId);

}
