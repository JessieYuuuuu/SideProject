package tw.jessie.sideproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.jessie.sideproject.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    //已有SQL基本語法
	
	Optional<Member>  findByAccount(String account);
}
