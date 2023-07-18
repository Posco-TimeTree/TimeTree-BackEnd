package pack.springjpa1.common.security;

import pack.springjpa1.common.security.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
}
