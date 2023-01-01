package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        //
        memberRepository= new MemoryMemberRepository();
        memberService= new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        //테스트 의존 관계없이 테스트 하기 위해 공용 데이터 삭제
        memberRepository.clearStore();
    }
    @Test
    void join() {
        //given
        Member member1 = new Member();
        member1.setName("hello");

        //when
        Long saveId = memberService.join(member1);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member1.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void duplicateTest(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
        /*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}