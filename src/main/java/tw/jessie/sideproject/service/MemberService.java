package tw.jessie.sideproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.jessie.sideproject.model.Member;
import tw.jessie.sideproject.repository.MemberRepository;
import tw.jessie.sideproject.utils.BCrypt;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

//  新增會員
    public void addMember(Member member) {
//  將密碼加密
        member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        memberRepository.save(member);
    }
//  會員登入核對資料
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
//  首頁隨機抓取會員資料
    public List<Member> getRandomMembers() {
		List<Member> allMembers = memberRepository.findRandomMembers();
//		只取前6個
		return allMembers.stream().limit(6).collect(Collectors.toList());
	}
//	利用會員id抓取會員資料
	public List<Member> getMemberById(Long memberid){
		return memberRepository.findMemberByMemberId(memberid);
	}
}
