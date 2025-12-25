package assignment.wif3006cbse.features.community.application.service.impl;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;
import assignment.wif3006cbse.features.community.application.service.ThreadService;
import assignment.wif3006cbse.features.community.domain.entity.Thread;
import assignment.wif3006cbse.features.community.domain.repository.ThreadRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = ThreadService.class)
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;

    @Activate
    public ThreadServiceImpl(@Reference ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @Override
    public ThreadModel createThread(CreateThreadModel createThreadModel) {
        Thread thread = new Thread(
            createThreadModel.content(),
            createThreadModel.authorId(),
            createThreadModel.postId()
        );
        Thread saved = threadRepository.save(thread);
        System.out.println("Created thread: " + saved.getId());
        return toModel(saved);
    }

    @Override
    public ThreadModel findThreadById(String id) {
        Thread thread = threadRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Thread not found: " + id));
        return toModel(thread);
    }

    @Override
    public List<ThreadModel> findThreadsByAuthorId(String authorId) {
        return threadRepository.findAllByAuthorId(authorId).stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public List<ThreadModel> findThreadsByPostId(String postId) {
        return threadRepository.findAllByPostId(postId).stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public List<ThreadModel> findAllThreads() {
        return threadRepository.findAll().stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public ThreadModel updateThread(UpdateThreadModel updateThreadModel) {
        Thread thread = threadRepository.findById(updateThreadModel.id())
            .orElseThrow(
                () -> new IllegalArgumentException("Thread not found: " + updateThreadModel.id()));

        thread.setContent(updateThreadModel.content());
        Thread saved = threadRepository.save(thread);
        System.out.println("Updated thread: " + saved.getId());
        return toModel(saved);
    }

    @Override
    public void deleteThreadById(String id) {
        threadRepository.deleteById(id);
        System.out.println("Deleted thread: " + id);
    }

    private ThreadModel toModel(Thread thread) {
        return new ThreadModel(
            thread.getId(),
            thread.getContent(),
            thread.getAuthorId(),
            thread.getPostId()
        );
    }
}
