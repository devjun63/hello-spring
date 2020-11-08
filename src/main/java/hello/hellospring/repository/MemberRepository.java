package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);   //Optional -> Null반환가능성이 있음 (이를 추가로 처리해서 반환)
    List<Member> findAll();
}
