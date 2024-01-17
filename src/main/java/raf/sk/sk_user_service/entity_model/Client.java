package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends User {

    public Client() {
        membershipCards = new ArrayList<>();
    }

    @JoinColumn(referencedColumnName = "id", name = "owner_fk")
    @OneToMany(targetEntity = MembershipCard.class, cascade = CascadeType.ALL)
    private List<MembershipCard> membershipCards;



    public void setMemberCards(List<MembershipCard> membershipCards) {
        this.membershipCards = membershipCards;
    }

    public List<MembershipCard> getMemberCards() {
        return membershipCards;
    }

    public void addMemberCard(MembershipCard membershipCard) {
        membershipCards.add(membershipCard);
    }


}
