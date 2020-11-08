package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
        // 실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수일 경우 ConcurrentHashMap을 사용해야 하지만 예제이므로 해쉬맵을 사용
    private static Long sequence = 0L;
    // 이 역시 실무에서는 automoicLong? (어텀롱) 등을 사용해야 하나 예제이므로 0L사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // ramda store에서 member.getName이 parameter에서 넘어온 name과 같은지 비교
        // stream() -> loop로 돌림 filter(if같은거?)
        // findAny 하나라도 찾는 것 이것이 optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
