package io.namoosori.travelclub.web.store.mapstore;

import io.namoosori.travelclub.web.aggregate.club.Membership;
import io.namoosori.travelclub.web.store.MembershipStore;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MembershipMapStore implements MembershipStore {
	//
	private Map<String, Membership> membershipMap;

	public MembershipMapStore() {
		//
		this.membershipMap = new LinkedHashMap<>();
	}

	@Override
	public String create(Membership membership) {
		//
		membershipMap.put(membership.getId(), membership);
		return membership.getId();
	}

	@Override
	public Membership retrieve(String membershipId) {
		//
		return membershipMap.get(membershipId);
	}

	@Override
	public Membership retrieveByClubIdAndMemberId(String clubId, String memberId) {
		//
		Membership targetMembership = null;

		for (Membership membership : membershipMap.values()) {
			if (membership.getClubId().equals(clubId) && membership.getMemberId().equals(memberId)) {
				targetMembership = membership;
				break;
			}
		}

		return targetMembership;
	}

	@Override
	public List<Membership> retrieveByClubId(String clubId) {
		//
		return membershipMap.values().stream()
				.filter(membership -> membership.getClubId().equals(clubId))
				.collect(Collectors.toList());
	}

	@Override
	public List<Membership> retrieveByMemberId(String memberId) {
		//
		return membershipMap.values().stream()
				.filter(membership -> membership.getMemberId().equals(memberId))
				.collect(Collectors.toList());
	}

	@Override
	public void update(Membership membership) {
		//
		membershipMap.put(membership.getId(), membership);
	}

	@Override
	public void delete(String membershipId) {
		//
		membershipMap.remove(membershipId);
	}

	@Override
	public boolean exists(String membershipId) {
		//
		return Optional.ofNullable(retrieve(membershipId)).isPresent();
	}
}
