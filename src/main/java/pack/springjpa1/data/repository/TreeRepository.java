package pack.springjpa1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.springjpa1.data.entity.TreeEntity;

public interface TreeRepository extends JpaRepository<TreeEntity, Long> {
    TreeEntity findByUserId(Long userId);
}

