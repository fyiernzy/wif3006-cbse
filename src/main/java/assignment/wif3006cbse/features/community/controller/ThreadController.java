package assignment.wif3006cbse.features.community.controller;

import assignment.wif3006cbse.features.community.dto.thread.CreateThreadEntityModel;
import assignment.wif3006cbse.features.community.dto.thread.ThreadEntityModel;
import assignment.wif3006cbse.features.community.dto.thread.UpdateThreadEntityModel;
import assignment.wif3006cbse.features.community.service.ThreadEntityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/thread")
public class ThreadController {

    private final ThreadEntityService threadEntityService;

    @PostMapping
    public ThreadEntityModel createThread(@RequestBody @Valid CreateThreadEntityModel createThreadEntityModel) {
        return threadEntityService.createThread(createThreadEntityModel);
    }

    @GetMapping("/{postId}")
    public PagedModel<ThreadEntityModel> findThreadsByPostId(@PathVariable @NotBlank String postId,
                                                            @PageableDefault Pageable pageable) {
        return new PagedModel<>(threadEntityService.findThreadsByPostId(postId, pageable));
    }

    @PutMapping
    public ThreadEntityModel updateThread(@RequestBody @Valid UpdateThreadEntityModel updateThreadModel) {
        return threadEntityService.updateThread(updateThreadModel);
    }

    @DeleteMapping("/{id}")
    public void deleteThread(@PathVariable @NotBlank String id) {
        threadEntityService.deleteThreadById(id);
    }
}
