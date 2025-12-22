package assignment.wif3006cbse.features.community.service;

import assignment.wif3006cbse.features.community.domain.entity.ThreadEntity;
import assignment.wif3006cbse.features.community.domain.repository.ThreadEntityRepository;
import assignment.wif3006cbse.features.community.dto.thread.CreateThreadEntityModel;
import assignment.wif3006cbse.features.community.dto.thread.ThreadEntityModel;
import assignment.wif3006cbse.features.community.dto.thread.UpdateThreadEntityModel;
import assignment.wif3006cbse.features.community.mapper.ThreadEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ThreadEntityService {

    private final ThreadEntityMapper threadEntityMapper;
    private final ThreadEntityRepository threadEntityRepository;

    @Transactional
    public ThreadEntityModel createThread(@Valid CreateThreadEntityModel createThreadEntityModel) {
        ThreadEntity thread = threadEntityMapper.toEntity(createThreadEntityModel);
        return threadEntityMapper.toModel(threadEntityRepository.save(thread));
    }

    @Transactional(readOnly = true)
    public Page<ThreadEntityModel> findThreadsByPostId(@NotNull String postId,
                                                       @NotNull Pageable pageable) {
        return threadEntityRepository.findAllByPostId(postId, pageable)
            .map(threadEntityMapper::toModel);
    }

    @Transactional
    public ThreadEntityModel updateThread(@Valid UpdateThreadEntityModel updateThreadEntityModel) {
        ThreadEntity thread = threadEntityRepository.findById(updateThreadEntityModel.id())
            .orElseThrow(() -> new EntityNotFoundException("Thread not found."));
        threadEntityMapper.updateEntityFromUpdateModel(thread, updateThreadEntityModel);
        return threadEntityMapper.toModel(threadEntityRepository.save(thread));
    }

    @Transactional
    public void deleteThreadById(@NotNull String id) {
        threadEntityRepository.deleteById(id);
    }
}
