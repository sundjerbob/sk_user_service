package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends User {

    public Client() {
        memberCards = new ArrayList<>();
    }

    @JoinColumn(name = "member_cards")
    @OneToMany
    private List<MemberCard> memberCards;

    @Column(name = "scheduled_trainings", nullable = false)
    private int scheduledTrainings;


    public void setMemberCards(List<MemberCard> memberCards) {
        this.memberCards = memberCards;
    }

    public List<MemberCard> getMemberCards() {
        return memberCards;
    }

    public void addMemberCard(MemberCard memberCard) {
        memberCards.add(memberCard);
    }


    public int getScheduledTrainings() {
        return scheduledTrainings;
    }

    public void setScheduledTrainings(int scheduledTrainings) {
        this.scheduledTrainings = scheduledTrainings;
    }
}
