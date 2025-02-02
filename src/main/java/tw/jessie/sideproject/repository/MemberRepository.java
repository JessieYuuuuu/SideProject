package tw.jessie.sideproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.jessie.sideproject.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> { //已有SQL基本語法
    
	
	Optional<Member>  findByAccount(String account); //抓取用戶輸入的帳號

	@Query("SELECT m FROM Member m ORDER BY FUNCTION('RAND')")
	List<Member> findRandomMembers();

	@Query("SELECT m FROM Member m WHERE m.memberid = :memberId")
	List<Member> findMemberByMemberId(Long memberId);

}
