package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;



    @Test
    void join() {
        //given
        Member member1 = new Member();
        member1.setName("hello2");

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
        member1.setName("spring123");

        Member member2 = new Member();
        member2.setName("spring12");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");

    }


}