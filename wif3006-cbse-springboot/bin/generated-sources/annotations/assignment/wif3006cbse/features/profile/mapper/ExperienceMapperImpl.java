package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.features.profile.domain.entity.Experience;
import assignment.wif3006cbse.features.profile.dto.experience.CreateExperienceModel;
import assignment.wif3006cbse.features.profile.dto.experience.ExperienceModel;
import assignment.wif3006cbse.features.profile.dto.experience.UpdateExperienceModel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T21:43:50+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ExperienceMapperImpl implements ExperienceMapper {

    @Override
    public ExperienceModel toModel(Experience experience) {
        if ( experience == null ) {
            return null;
        }

        boolean isCurrent = false;
        String id = null;
        String userId = null;
        String jobTitle = null;
        String company = null;
        String location = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        String description = null;

        isCurrent = experience.isCurrent();
        if ( experience.getId() != null ) {
            id = experience.getId();
        }
        if ( experience.getUserId() != null ) {
            userId = experience.getUserId();
        }
        if ( experience.getJobTitle() != null ) {
            jobTitle = experience.getJobTitle();
        }
        if ( experience.getCompany() != null ) {
            company = experience.getCompany();
        }
        if ( experience.getLocation() != null ) {
            location = experience.getLocation();
        }
        if ( experience.getStartDate() != null ) {
            startDate = experience.getStartDate();
        }
        if ( experience.getEndDate() != null ) {
            endDate = experience.getEndDate();
        }
        if ( experience.getDescription() != null ) {
            description = experience.getDescription();
        }

        ExperienceModel experienceModel = new ExperienceModel( id, userId, jobTitle, company, location, startDate, endDate, isCurrent, description );

        return experienceModel;
    }

    @Override
    public Experience toEntity(CreateExperienceModel createExperienceModel) {
        if ( createExperienceModel == null ) {
            return null;
        }

        Experience experience = new Experience();

        if ( createExperienceModel.isCurrent() != null ) {
            experience.setCurrent( createExperienceModel.isCurrent() );
        }
        if ( createExperienceModel.userId() != null ) {
            experience.setUserId( createExperienceModel.userId() );
        }
        if ( createExperienceModel.jobTitle() != null ) {
            experience.setJobTitle( createExperienceModel.jobTitle() );
        }
        if ( createExperienceModel.company() != null ) {
            experience.setCompany( createExperienceModel.company() );
        }
        if ( createExperienceModel.location() != null ) {
            experience.setLocation( createExperienceModel.location() );
        }
        if ( createExperienceModel.startDate() != null ) {
            experience.setStartDate( createExperienceModel.startDate() );
        }
        if ( createExperienceModel.endDate() != null ) {
            experience.setEndDate( createExperienceModel.endDate() );
        }
        if ( createExperienceModel.description() != null ) {
            experience.setDescription( createExperienceModel.description() );
        }

        return experience;
    }

    @Override
    public void updateEntityFromUpdateModel(Experience experience, UpdateExperienceModel updateExperienceModel) {
        if ( updateExperienceModel == null ) {
            return;
        }

        if ( updateExperienceModel.isCurrent() != null ) {
            experience.setCurrent( updateExperienceModel.isCurrent() );
        }
        if ( updateExperienceModel.jobTitle() != null ) {
            experience.setJobTitle( updateExperienceModel.jobTitle() );
        }
        if ( updateExperienceModel.company() != null ) {
            experience.setCompany( updateExperienceModel.company() );
        }
        if ( updateExperienceModel.location() != null ) {
            experience.setLocation( updateExperienceModel.location() );
        }
        if ( updateExperienceModel.startDate() != null ) {
            experience.setStartDate( updateExperienceModel.startDate() );
        }
        if ( updateExperienceModel.endDate() != null ) {
            experience.setEndDate( updateExperienceModel.endDate() );
        }
        if ( updateExperienceModel.description() != null ) {
            experience.setDescription( updateExperienceModel.description() );
        }
    }
}
