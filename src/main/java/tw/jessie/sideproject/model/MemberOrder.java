package tw.jessie.sideproject.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "memberorder")
public class MemberOrder {

    @EmbeddedId
    private MemberOrderKey id;
    
	@ManyToOne
    @MapsId("memberid")
    @JoinColumn(name = "memberid", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @MapsId("orderid")
    @JoinColumn(name = "orderid", insertable = false, updatable = false)
    private Order order;

    private Boolean wanted;
    
    private Boolean collected;

	public MemberOrderKey getId() {
		return id;
	}

	public void setId(MemberOrderKey id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Boolean getWanted() {
		return wanted;
	}

	public void setWanted(Boolean wanted) {
		this.wanted = wanted;
	}

	public Boolean getCollected() {
		return collected;
	}

	public void setCollected(Boolean collected) {
		this.collected = collected;
	}

	
	
    
}
