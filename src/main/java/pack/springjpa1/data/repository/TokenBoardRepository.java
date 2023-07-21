package pack.springjpa1.data.repository;

import pack.springjpa1.data.entity.TokenBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenBoardRepository extends JpaRepository<TokenBoard, Long> {
    Optional<TokenBoard> findByMemberEmailAndMemberId(String email,Long memberId);
}

