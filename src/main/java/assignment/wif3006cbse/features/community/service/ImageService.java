//package assignment.wif3006cbse.features.community.service;
//
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.annotation.Validated;
//
//@Slf4j
//@Service
//@Validated
//@RequiredArgsConstructor
//public class ImageService {
//
//    private final ImageMapper imageMapper;
//    private final ImageRepository imageRepository;
//
//    @Transactional
//    public ImageModel createImage(@Valid CreateImageModel createImageModel) {
//        Image image = imageMapper.toEntity(createImageModel);
//        return imageMapper.toModel(imageRepository.save(image));
//    }
//
//    @Transactional(readOnly = true)
//    public ImageModel findImageById(@NotNull Long id) {
//        Image image = imageRepository.findById(id)
//            .orElseThrow(() -> new EntityNotFoundException("Image not found."));
//        return imageMapper.toModel(image);
//    }
//
//    @Transactional
//    public ImageModel updateImage(@Valid UpdateImageModel updateImageModel) {
//        Image image = imageRepository.findById(updateImageModel.id())
//            .orElseThrow(() -> new EntityNotFoundException("Image not found."));
//        imageMapper.updateEntityFromUpdateModel(image, updateImageModel);
//        return imageMapper.toModel(imageRepository.save(image));
//    }
//
//    @Transactional
//    public void deleteImageById(@NotNull Long id) {
//        imageRepository.deleteById(id);
//    }
//}
