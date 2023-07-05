package io.namoosori.travelclub.spring.store.mapstore;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.store.ClubStore;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("ClubStore")
public class ClubMapStore implements ClubStore {
    //Linked hash map에 저장
    private Map<String, TravelClub> clubMap;

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
    public List<TravelClub> retrieveByName(String name) {   // 리스트 형태로 반환되면 동일한 이름의 클럽도 다 반환할 수 있다.
        return clubMap.values().stream()                //  stream을 이용
                .filter(club -> club.getName().equals(name))        // 이름이 같은 값을 찾아서 필터링
                .collect(Collectors.toList());          // 리스트로 변환
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
    public boolean exists(String clubId){
//        return clubMap.containsKey(clubId);
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();
    }
}
