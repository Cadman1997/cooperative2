package com.cadman.cooperative.repository;

import com.cadman.cooperative.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findTopByOrderByIdDesc();
    Optional<Member> findByEmail(String email);
    Optional<Member> findByMemberIdAndPassword(String memberId, String password);
}
