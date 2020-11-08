package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 순서와 상관 없이 개별 메서드가 실행 되도록 해야함
    // 각 테스트 마다 memory 적재를 없애주는 과정이 필요
    // 저장소나 공용 데이터를 지워주자

    // 테스트 케이스 먼저 만들고 이후 실제 기능 구현 -> 테스트 주도 개발 TDD
    // 세모 모양 작품을 만들꺼야 -> 세모모양 틀을 먼저 만들기 -> TDD
    // 테스트가 수십 수백개 일 경우 -> gradlew test
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member));
        //Assertions.assertEquals(result, null);
        // Test Result 에서 맞다면 녹색 V표시 틀리면 X
        assertThat(member).isEqualTo(result);
        //org.assertj.core.api 좀 더 편하게 쓰는 Test library
        // static import 쓰는 assertThat 바로 쓸 수 있음
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
