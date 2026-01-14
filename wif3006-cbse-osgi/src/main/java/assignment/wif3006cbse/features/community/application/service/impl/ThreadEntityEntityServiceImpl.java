package assignment.wif3006cbse.features.community.application.service.impl;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;
import assignment.wif3006cbse.features.community.application.service.ThreadEntityService;
import assignment.wif3006cbse.features.community.domain.entity.ThreadEntity;
import assignment.wif3006cbse.features.community.domain.repository.ThreadEntityRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = ThreadEntityService.class)
public class ThreadEntityEntityServiceImpl implements ThreadEntityService {

    private final ThreadEntityRepository threadEntityRepository;

    @Activate
    public ThreadEntityEntityServiceImpl(@Reference ThreadEntityRepository threadEntityRepository) {
        this.threadEntityRepository = threadEntityRepository;
    }

    @Override
    public ThreadModel createThread(CreateThreadModel createThreadModel) {
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
    public ThreadModel findThreadById(String id) {
        ThreadEntity threadEntity = threadEntityRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Thread not found: " + id));
        return toModel(threadEntity);
    }

    @Override
    public List<ThreadModel> findThreadsByAuthorId(String authorId) {
        return threadEntityRepository.findAllByAuthorId(authorId).stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public List<ThreadModel> findThreadsByPostId(String postId) {
        return threadEntityRepository.findAllByPostId(postId).stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public List<ThreadModel> findAllThreads() {
        return threadEntityRepository.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public ThreadModel updateThread(UpdateThreadModel updateThreadModel) {
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

    private ThreadModel toModel(ThreadEntity threadEntity) {
        return new ThreadModel(
            threadEntity.getId(),
            threadEntity.getContent(),
            threadEntity.getAuthorId(),
            threadEntity.getPostId()
        );
    }
}
