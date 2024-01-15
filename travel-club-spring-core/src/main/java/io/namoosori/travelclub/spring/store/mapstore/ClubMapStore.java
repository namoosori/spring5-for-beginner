package io.namoosori.travelclub.spring.store.mapstore;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.store.ClubStore;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("clubStore")
public class ClubMapStore implements ClubStore {

    private final Map<String, TravelClub> clubMap;

    public ClubMapStore(){
        this.clubMap = new LinkedHashMap<>();
    }

    @Override
    public String create(TravelClub club) {
        clubMap.put(club.getId(), club);
        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        return clubMap.get(clubId);
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {
        return clubMap.values().stream()
                .filter(club -> club.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        clubMap.put(club.getId(), club);
    }

    @Override
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    @Override
    public boolean exists(String clubId) {
//        return clubMap.containsKey(clubId);
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();
    }
}
