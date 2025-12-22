package assignment.wif3006cbse.features.file.application.dto;

public record FileModel(
    String id,
    String fileName,
    String contentType,
    Long fileSizeBytes
) {

}
