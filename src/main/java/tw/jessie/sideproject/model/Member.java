package tw.jessie.sideproject.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberid;
	@NotEmpty(message = "帳號不可為空")
	private String account;
	@NotEmpty(message = "密碼不可為空")
	private String password;
	@NotEmpty(message = "姓名不可為空")
	private String email;
	private String name;
	private String birthday;
	private String tel;
	private String intro;
	private String github;
	private String picurl;
	@Column(nullable = false)
	private Boolean isblocked = false; // 預設值

	@ManyToMany
	@JoinTable(name = "membertag", joinColumns = @JoinColumn(name = "memberid"), inverseJoinColumns = @JoinColumn(name = "tagid"))
	private Set<Tag> tags = new HashSet<>();

	public Long getMemberid() {
		return memberid;
	}

	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return password;
	}

	public void setPasswd(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public Boolean getIsblocked() {
		return isblocked;
	}

	public void setIsblocked(Boolean isblocked) {
		this.isblocked = isblocked;
	}

	public String getTagNames() {
		return tags.stream().map(Tag::getTagname).collect(Collectors.joining(","));
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

}
