package io.namoosori.travelclub.web.store;

import io.namoosori.travelclub.web.aggregate.club.Membership;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MembershipStore {
    //
    String create(Membership membership);
    Membership retrieve(String membershipId);
    Membership retrieveByClubIdAndMemberId(String clubId, String memberId);
    List<Membership> retrieveByClubId(String clubId);
    List<Membership> retrieveByMemberId(String memberId);
    void update(Membership membership);
    void delete(String membershipId);

    boolean exists(String membershipId);
}
