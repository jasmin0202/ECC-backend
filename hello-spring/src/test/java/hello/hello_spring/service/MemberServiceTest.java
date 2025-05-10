package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void BeforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 같은 메모리 사용. 직접 new하는게 아니라 외부에서 리포지토리를 넣어준다 : 이것을 DI라고 함
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 실제 동작하는 코드 말고 걍 테스트는 함수명 한글로 해도 됨ㅋ
        //given(주어지는 데이터)
        Member member = new Member();
        member.setName("니얼굴");

        //when(검증하는 대상)
        Long saveId = memberService.join(member);

        //then(검증부)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals("니얼굴", findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

}

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}