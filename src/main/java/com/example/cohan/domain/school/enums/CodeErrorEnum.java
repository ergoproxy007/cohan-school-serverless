package com.example.cohan.domain.school.enums;

import lombok.Getter;

@Getter
public enum CodeErrorEnum {

    PERSON_SAVE_INTERNAL_ERROR("PERSON_SAVE_001", OriginEnum.PERSON_SAVE_INTERACTOR),
    PERSON_SAVE_IF_EXISTS("PERSON_SAVE_002", OriginEnum.PERSON_SAVE_INTERACTOR),
    PERSON_SAVE_TECHNICAL_ERROR("PERSON_SAVE_003", OriginEnum.PERSON_SAVE_INTERACTOR),
    PERSON_RULE_BAD_REQUEST("PERSON_RULE_001", OriginEnum.TEACHER_SERVICE),
    PERSON_FIND_BUSINESS_ERROR("PERSON_FIND_001", OriginEnum.PERSON_SAVE_INTERACTOR),
    PERSON_FIND_ERROR("PERSON_FIND_002", OriginEnum.PERSON_SAVE_INTERACTOR),
    STUDENT_FIND_BY_DNI_ERROR("STUDENT_FIND_BY_DNI_ERROR", OriginEnum.PERSON_SAVE_INTERACTOR),
    TEACHER_FIND_BY_DNI_ERROR("TEACHER_FIND_BY_DNI_ERROR", OriginEnum.PERSON_SAVE_INTERACTOR),
    PERSON_UPDATE_INTERNAL_ERROR("PERSON_SAVE_001", OriginEnum.PERSON_SAVE_INTERACTOR);

    private final String code;
    private final String origin;

    CodeErrorEnum(String code, String origin) {
        this.code = code;
        this.origin = origin;
    }
}
