package assignment.wif3006cbse.features.community.application.service.impl;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadEntityModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;
import assignment.wif3006cbse.features.community.application.service.ThreadEntityService;
import assignment.wif3006cbse.features.community.domain.entity.ThreadEntity;
import assignment.wif3006cbse.features.community.domain.repository.ThreadEntityRepository;
import assignment.wif3006cbse.shared.pagination.Page;
import assignment.wif3006cbse.shared.pagination.PageUtils;
import assignment.wif3006cbse.shared.pagination.Pageable;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component(service = ThreadEntityService.class)
public class ThreadEntityServiceImpl implements ThreadEntityService {

    private final ThreadEntityRepository threadEntityRepository;

    @Activate
    public ThreadEntityServiceImpl(@Reference ThreadEntityRepository threadEntityRepository) {
        this.threadEntityRepository = threadEntityRepository;
    }

    @Override
    public ThreadEntityModel createThread(CreateThreadModel createThreadModel) {
        ThreadEntity threadEntity = new ThreadEntity(
            createThreadModel.content(),
            createThreadModel.authorId(),
            createThreadModel.postId()
        );
        ThreadEntity saved = threadEntityRepository.save(threadEntity);
        System.out.println("Created thread: " + saved.getId());
        return toModel(saved);
    }

    @Override
    public Page<ThreadEntityModel> findThreadsByPostId(String postId, Pageable pageable) {
        return PageUtils.toPage(threadEntityRepository.findAllByPostId(postId).stream()
            .map(this::toModel)
            .collect(Collectors.toList()), pageable);
    }

    @Override
    public ThreadEntityModel updateThread(UpdateThreadModel updateThreadModel) {
        ThreadEntity threadEntity = threadEntityRepository.findById(updateThreadModel.id())
            .orElseThrow(
                () -> new IllegalArgumentException("Thread not found: " + updateThreadModel.id()));

        threadEntity.setContent(updateThreadModel.content());
        threadEntity.setUpdatedAt(LocalDateTime.now());
        ThreadEntity saved = threadEntityRepository.save(threadEntity);
        System.out.println("Updated thread: " + saved.getId());
        return toModel(saved);
    }

    @Override
    public void deleteThreadById(String id) {
        threadEntityRepository.deleteById(id);
        System.out.println("Deleted thread: " + id);
    }

    private ThreadEntityModel toModel(ThreadEntity threadEntity) {
        return new ThreadEntityModel(
            threadEntity.getId(),
            threadEntity.getContent(),
            threadEntity.getAuthorId(),
            threadEntity.getPostId()
        );
    }
}
