package pack.springjpa1.data.repository;

import pack.springjpa1.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByUuid(String uniqueKey);
    public Optional<Member> findByName(String name);
    public Optional<Member> findByNameAndEmail(String name, String email);
}
