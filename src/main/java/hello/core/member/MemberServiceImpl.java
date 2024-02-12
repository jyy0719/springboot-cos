package hello.core.member;
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // MemberRepository객체를 공통으로 사용하고 있는지 검증용도로 넣은 매서드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}