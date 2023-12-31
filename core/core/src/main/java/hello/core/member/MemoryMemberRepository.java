package hello.core.member;
import hello.core.discount.DiscountPolicy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store=new HashMap<>();
    //실제로는 동시성 이슈 때문에 ConcurrentHashMap을 사용

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
