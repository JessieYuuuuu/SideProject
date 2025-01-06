package tw.jessie.sideproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.jessie.sideproject.model.Member;
import tw.jessie.sideproject.repository.MemberRepository;
import tw.jessie.sideproject.utils.BCrypt;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void addMember(Member member) {
    	//將密碼加密
        member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        memberRepository.save(member);
    }
    public Member loginMember(Member loginMember) {
    	Optional<Member> opt = memberRepository.findByAccount(loginMember.getAccount());
		Member member = opt.get();
		if (member != null) {
			if (!BCrypt.checkpw(loginMember.getPasswd(), member.getPasswd())) {
				member = null;
			}
		}
		return member;
    }
}
