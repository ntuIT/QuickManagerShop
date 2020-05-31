package com.qman.web.validator.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qman.web.constant.biz.ProfileBizConstant;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.glossary.RelationStatus;

@Component
public class ProfileValidator {

    @Autowired
    private MasterDataStorage mdStorage;

    public Boolean validateLastName(String name) {
        return (name != null && !name.isEmpty() && name.length() < 50);
    }

    public Boolean validateMiddleName(String name) {
        return (name != null && !name.isEmpty() && name.length() < 50);
    }

    public Boolean validateFirstName(String name) {
        return (name != null && !name.isEmpty() && name.length() < 50);
    }

    public Boolean validateBirthday(String birthday) {
        if (birthday == null || "".equals(birthday)) return false;
        SimpleDateFormat sdf = new SimpleDateFormat(ProfileBizConstant.BRITHDAY_FORMAT);
        try {
            Date birthDayDate = sdf.parse(birthday);
            return birthDayDate != null;
        } catch (ParseException e) {
            return false;
        }
    }

    public Pair<Boolean, Gender> validateGender(Byte gender) {
        if (gender == null) return Pair.with(false, null);
        Gender parsedGender = Gender.parse(gender);
        if (parsedGender == null) return Pair.with(false, null);
        return Pair.with(true, parsedGender);
    }

    public Pair<Boolean, RelationStatus> validateRelationStatus(Byte relationStatus) {
        if (relationStatus == null) return Pair.with(false, null);
        RelationStatus parsedRelationStatus = RelationStatus.parse(relationStatus);
        if (parsedRelationStatus == null) return Pair.with(false, null);
        return Pair.with(true, parsedRelationStatus);
    }

    public Boolean validateInterestList(List<Integer> interestIds) {
        if (interestIds == null || interestIds.isEmpty() || interestIds.size() < ProfileBizConstant.REQUIRED_INTEREST) return false;
        Set<Integer> categoryIdSet = mdStorage.getCategoryList().stream().map(item -> item.getId()).collect(Collectors.toSet());
        return categoryIdSet.containsAll(interestIds);
    }

    public Boolean validateHateList(List<Integer> hateIds) {
        // we don't require user to choose Hate List but if user select them,
        // check if they are in Master Data list
        if (hateIds != null && !hateIds.isEmpty()) {
            Set<Integer> categoryIdSet = mdStorage.getCategoryList().stream().map(item -> item.getId()).collect(Collectors.toSet());
            return categoryIdSet.containsAll(hateIds);
        }
        return true;
    }

    public Boolean isInterestHateSame(List<Integer> interestList, List<Integer> hateList) {
        // Check if client send same CategoryId in both list InterestIdList and
        // HateIdList
        List<Integer> concatOfInterestHateId = new ArrayList<Integer>(interestList);
        concatOfInterestHateId.addAll(hateList);
        Integer originalSize = concatOfInterestHateId.size();
        HashSet<Integer> distinctConcat = new HashSet<Integer>(concatOfInterestHateId);
        Integer newSize = distinctConcat.size();
        return newSize < originalSize;
    }
}
