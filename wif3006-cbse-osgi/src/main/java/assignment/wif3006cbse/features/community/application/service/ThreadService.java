package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;

import java.util.List;

public interface ThreadService {

    ThreadModel createThread(CreateThreadModel createThreadModel);

    ThreadModel findThreadById(String id);

    List<ThreadModel> findThreadsByAuthorId(String authorId);

    List<ThreadModel> findThreadsByPostId(String postId);

    List<ThreadModel> findAllThreads();

    ThreadModel updateThread(UpdateThreadModel updateThreadModel);

    void deleteThreadById(String id);
}
