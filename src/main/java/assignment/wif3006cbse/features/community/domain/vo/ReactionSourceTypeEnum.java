package assignment.wif3006cbse.features.community.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReactionSourceTypeEnum {
    POST("POST"),
    THREAD("THREAD"),
    COMMENT("COMMENT"),
    ;
    private final String value;
}
