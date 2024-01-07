package raf.sk.sk_user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.sk.sk_user_service.entity_model.MembershipCard;

public interface MemberCardRepository extends JpaRepository<MembershipCard, Long> {



}
