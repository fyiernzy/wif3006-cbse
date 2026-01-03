package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.features.profile.domain.entity.JobHistory;
import assignment.wif3006cbse.features.profile.dto.jobhistory.JobHistoryModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T21:43:03+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class JobHistoryMapperImpl implements JobHistoryMapper {

    @Override
    public JobHistoryModel toModel(JobHistory jobHistory) {
        if ( jobHistory == null ) {
            return null;
        }

        String id = null;
        String userId = null;
        String projectId = null;
        String projectName = null;
        String role = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        String status = null;
        Integer rating = null;
        String review = null;
        List<String> deliverables = null;

        if ( jobHistory.getId() != null ) {
            id = jobHistory.getId();
        }
        if ( jobHistory.getUserId() != null ) {
            userId = jobHistory.getUserId();
        }
        if ( jobHistory.getProjectId() != null ) {
            projectId = jobHistory.getProjectId();
        }
        if ( jobHistory.getProjectName() != null ) {
            projectName = jobHistory.getProjectName();
        }
        if ( jobHistory.getRole() != null ) {
            role = jobHistory.getRole();
        }
        if ( jobHistory.getStartDate() != null ) {
            startDate = jobHistory.getStartDate();
        }
        if ( jobHistory.getEndDate() != null ) {
            endDate = jobHistory.getEndDate();
        }
        if ( jobHistory.getStatus() != null ) {
            status = jobHistory.getStatus();
        }
        if ( jobHistory.getRating() != null ) {
            rating = jobHistory.getRating();
        }
        if ( jobHistory.getReview() != null ) {
            review = jobHistory.getReview();
        }
        List<String> list = jobHistory.getDeliverables();
        if ( list != null ) {
            deliverables = new ArrayList<String>( list );
        }

        JobHistoryModel jobHistoryModel = new JobHistoryModel( id, userId, projectId, projectName, role, startDate, endDate, status, rating, review, deliverables );

        return jobHistoryModel;
    }
}
