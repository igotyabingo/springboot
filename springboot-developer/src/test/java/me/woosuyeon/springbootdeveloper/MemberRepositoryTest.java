package me.woosuyeon.springbootdeveloper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    // 1. 모든 데이터 조회
    @Sql("/insert-members.sql")
    @Test
    void getAllMembers() {
        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members.size()).isEqualTo(3);
    }

    // 2. 기본키로 조회
    @Sql("/insert-members.sql")
    @Test
    void getMemberById() {
        // when
        Member member = memberRepository.findById(2L).get();

        // then
        assertThat(member.getName()).isEqualTo("B");
    }

    // 3. 다른 컬럼으로 조회 (인터페이스에 오버라이드 메소드 작성)
    @Sql("/insert-members.sql")
    @Test
    void getMemberByName() {
        // when
        Member member = memberRepository.findByName("B").get();

        // then
        assertThat(member.getId()).isEqualTo(2L);
    }

    // 4. 추가 메소드 (단일)
    @Test
    void saveMember() {
        // given
        Member member = new Member(1L, "A");

        // when
        memberRepository.save(member);

        // then
        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }

    // 5. 추가 메소드 (여러개)
    @Test
    void saveMembers() {
        // given
        List<Member> members = List.of(new Member(2L, "B"), new Member(3L, "C"));

        // when
        memberRepository.saveAll(members);

        // then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);

    }

    // 6. 삭제 메소드 (단일)
    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById() {
        // when
        memberRepository.deleteById(2L);

        // then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    // 7. 삭제 메소드 (전체)
    @Sql("/insert-members.sql")
    @Test
    void deleteAllMembers() {
        // when
        memberRepository.deleteAll();

        // then
        assertThat(memberRepository.findAll().size()).isZero();
    }

    // 8. 수정 메소드
    @
    @Sql("/insert-members.sql")
    @Test
    void update() {
        // given
        Member member = memberRepository.findById(2L).get();

        // when
        member.changeName("BC");

        // then
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }



}