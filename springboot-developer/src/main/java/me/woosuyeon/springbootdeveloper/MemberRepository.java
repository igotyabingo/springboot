package me.woosuyeon.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
