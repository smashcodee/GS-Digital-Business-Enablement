package br.com.smashcode.babycare.utils;

import br.com.smashcode.babycare.exception.DateInvalidException;
import br.com.smashcode.babycare.model.UserAccountEntity;
import br.com.smashcode.babycare.model.UserProfileEntity;
import br.com.smashcode.babycare.repository.IUserProfileRepository;
import br.com.smashcode.babycare.request.AccountRequest;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class UserProfileUtils {
    private static IUserProfileRepository userProfileRepository;
    private static Integer minimumAge = 10;

    public UserProfileUtils(IUserProfileRepository userProfileRepository) {
        UserProfileUtils.userProfileRepository = userProfileRepository;
    }


    public static boolean userProfileExists(UUID profileId) {
        var optional = userProfileRepository.findById(profileId);
        if(!optional.isPresent()) return false;
        return true;
    }

    public static boolean birthDateIsCorrect(LocalDate birth) {
        // Idade mínima para se utilizar o aplicativo (10 anos)
        Period originalDate = Period.between(birth, LocalDate.now());
        return originalDate.getYears() < minimumAge;
    }



    public static UserProfileEntity toEntity(AccountRequest request, UserAccountEntity account) {
        if(birthDateIsCorrect(request.getBirthDate())) throw new DateInvalidException("Informe uma data válida.");
        Integer originalAge = getAgeFromBirthDate(request.getBirthDate());
        var entity = new UserProfileEntity();
        entity.setAge(originalAge);
        entity.setFullName(request.getFullName());
        entity.setBloodType(request.getBloodType());
        entity.setAccount(account);
        return entity;
    }

    private static Integer getAgeFromBirthDate(LocalDate birth) {
        Period originalDate = Period.between(birth, LocalDate.now());
        return originalDate.getYears();
    }

}
