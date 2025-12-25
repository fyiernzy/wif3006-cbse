package assignment.wif3006cbse.features.community.domain.vo;

public enum ReactionSourceTypeEnum {
    POST("POST"),
    THREAD("THREAD"),
    COMMENT("COMMENT"),
    ;
    private final String value;

    ReactionSourceTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}