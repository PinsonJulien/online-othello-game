package com.pinson.othello.tokens;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
        SELECT t FROM Token t
        INNER JOIN OthelloPlayer p ON t.player.id = p.id
        WHERE
            p.id = :id
            AND
            (
                t.expired = FALSE
                OR
                t.revoked = FALSE
            )
      """)
    List<Token> findAllValidTokenByUser(Long id);

    Optional<Token> findByToken(String token);
}