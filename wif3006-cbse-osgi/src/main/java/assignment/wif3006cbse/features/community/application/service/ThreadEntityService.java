package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadEntityModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;

import java.util.List;

public interface ThreadEntityService {

    ThreadEntityModel createThread(CreateThreadModel createThreadModel);

    List<ThreadEntityModel> findThreadsByPostId(String postId);

    ThreadEntityModel updateThread(UpdateThreadModel updateThreadModel);

    void deleteThreadById(String id);
}
