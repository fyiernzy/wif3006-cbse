//package assignment.wif3006cbse.features.community.controller;
//
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@Validated
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/image")
//public class ImageController {
//
//    private final ImageService imageService;
//
//    @PostMapping
//    public ImageModel createImage(@RequestBody @Valid CreateImageModel createImageModel) {
//        return imageService.createImage(createImageModel);
//    }
//
//    @GetMapping("/{id}")
//    public ImageModel findImageById(@PathVariable @NotNull Long id) {
//        return imageService.findImageById(id);
//    }
//
//    @PutMapping
//    public ImageModel updateImage(@RequestBody @Valid UpdateImageModel updateImageModel) {
//        return imageService.updateImage(updateImageModel);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteImage(@PathVariable @NotNull Long id) {
//        imageService.deleteImageById(id);
//    }
//}
