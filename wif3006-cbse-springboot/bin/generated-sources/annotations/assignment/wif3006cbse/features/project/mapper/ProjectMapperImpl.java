package assignment.wif3006cbse.features.project.mapper;

import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.features.project.dto.project.CreateProjectModel;
import assignment.wif3006cbse.features.project.dto.project.ProjectModel;
import assignment.wif3006cbse.features.project.dto.project.UpdateProjectModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T14:49:17+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectModel toModel(Project project) {
        if ( project == null ) {
            return null;
        }

        String id = null;
        String postedBy = null;
        byte[] companyLogo = null;
        String companyName = null;
        String projectTitle = null;
        String projectDescription = null;
        String location = null;
        String projectCategory = null;
        String projectDuration = null;
        List<String> filter = null;
        String contactInformation = null;
        String additionalNotes = null;
        Date deadline = null;
        Double projectBudget = null;
        List<String> requiredSkills = null;
        Boolean agreedToTerms = null;
        Boolean posted = null;
        Boolean taken = null;
        Boolean completed = null;
        List<String> applicants = null;
        String serviceProvider = null;
        Boolean fileAccepted = null;

        if ( project.getId() != null ) {
            id = project.getId();
        }
        if ( project.getPostedBy() != null ) {
            postedBy = project.getPostedBy();
        }
        byte[] companyLogo1 = project.getCompanyLogo();
        if ( companyLogo1 != null ) {
            companyLogo = Arrays.copyOf( companyLogo1, companyLogo1.length );
        }
        if ( project.getCompanyName() != null ) {
            companyName = project.getCompanyName();
        }
        if ( project.getProjectTitle() != null ) {
            projectTitle = project.getProjectTitle();
        }
        if ( project.getProjectDescription() != null ) {
            projectDescription = project.getProjectDescription();
        }
        if ( project.getLocation() != null ) {
            location = project.getLocation();
        }
        if ( project.getProjectCategory() != null ) {
            projectCategory = project.getProjectCategory();
        }
        if ( project.getProjectDuration() != null ) {
            projectDuration = project.getProjectDuration();
        }
        List<String> list = project.getFilter();
        if ( list != null ) {
            filter = new ArrayList<String>( list );
        }
        if ( project.getContactInformation() != null ) {
            contactInformation = project.getContactInformation();
        }
        if ( project.getAdditionalNotes() != null ) {
            additionalNotes = project.getAdditionalNotes();
        }
        if ( project.getDeadline() != null ) {
            deadline = project.getDeadline();
        }
        if ( project.getProjectBudget() != null ) {
            projectBudget = project.getProjectBudget();
        }
        List<String> list1 = project.getRequiredSkills();
        if ( list1 != null ) {
            requiredSkills = new ArrayList<String>( list1 );
        }
        if ( project.getAgreedToTerms() != null ) {
            agreedToTerms = project.getAgreedToTerms();
        }
        if ( project.getPosted() != null ) {
            posted = project.getPosted();
        }
        if ( project.getTaken() != null ) {
            taken = project.getTaken();
        }
        if ( project.getCompleted() != null ) {
            completed = project.getCompleted();
        }
        List<String> list2 = project.getApplicants();
        if ( list2 != null ) {
            applicants = new ArrayList<String>( list2 );
        }
        if ( project.getServiceProvider() != null ) {
            serviceProvider = project.getServiceProvider();
        }
        if ( project.getFileAccepted() != null ) {
            fileAccepted = project.getFileAccepted();
        }

        ProjectModel projectModel = new ProjectModel( id, postedBy, companyLogo, companyName, projectTitle, projectDescription, location, projectCategory, projectDuration, filter, contactInformation, additionalNotes, deadline, projectBudget, requiredSkills, agreedToTerms, posted, taken, completed, applicants, serviceProvider, fileAccepted );

        return projectModel;
    }

    @Override
    public Project toEntity(CreateProjectModel createProjectModel) {
        if ( createProjectModel == null ) {
            return null;
        }

        Project project = new Project();

        if ( createProjectModel.additionalNotes() != null ) {
            project.setAdditionalNotes( createProjectModel.additionalNotes() );
        }
        if ( createProjectModel.agreedToTerms() != null ) {
            project.setAgreedToTerms( createProjectModel.agreedToTerms() );
        }
        List<String> list = createProjectModel.applicants();
        if ( list != null ) {
            project.setApplicants( new ArrayList<String>( list ) );
        }
        byte[] companyLogo = createProjectModel.companyLogo();
        if ( companyLogo != null ) {
            project.setCompanyLogo( Arrays.copyOf( companyLogo, companyLogo.length ) );
        }
        if ( createProjectModel.companyName() != null ) {
            project.setCompanyName( createProjectModel.companyName() );
        }
        if ( createProjectModel.completed() != null ) {
            project.setCompleted( createProjectModel.completed() );
        }
        if ( createProjectModel.contactInformation() != null ) {
            project.setContactInformation( createProjectModel.contactInformation() );
        }
        if ( createProjectModel.deadline() != null ) {
            project.setDeadline( createProjectModel.deadline() );
        }
        if ( createProjectModel.fileAccepted() != null ) {
            project.setFileAccepted( createProjectModel.fileAccepted() );
        }
        List<String> list1 = createProjectModel.filter();
        if ( list1 != null ) {
            project.setFilter( new ArrayList<String>( list1 ) );
        }
        if ( createProjectModel.location() != null ) {
            project.setLocation( createProjectModel.location() );
        }
        if ( createProjectModel.posted() != null ) {
            project.setPosted( createProjectModel.posted() );
        }
        if ( createProjectModel.postedBy() != null ) {
            project.setPostedBy( createProjectModel.postedBy() );
        }
        if ( createProjectModel.projectBudget() != null ) {
            project.setProjectBudget( createProjectModel.projectBudget() );
        }
        if ( createProjectModel.projectCategory() != null ) {
            project.setProjectCategory( createProjectModel.projectCategory() );
        }
        if ( createProjectModel.projectDescription() != null ) {
            project.setProjectDescription( createProjectModel.projectDescription() );
        }
        if ( createProjectModel.projectDuration() != null ) {
            project.setProjectDuration( createProjectModel.projectDuration() );
        }
        if ( createProjectModel.projectTitle() != null ) {
            project.setProjectTitle( createProjectModel.projectTitle() );
        }
        List<String> list2 = createProjectModel.requiredSkills();
        if ( list2 != null ) {
            project.setRequiredSkills( new ArrayList<String>( list2 ) );
        }
        if ( createProjectModel.serviceProvider() != null ) {
            project.setServiceProvider( createProjectModel.serviceProvider() );
        }
        if ( createProjectModel.taken() != null ) {
            project.setTaken( createProjectModel.taken() );
        }

        return project;
    }

    @Override
    public void updateEntityFromUpdateModel(Project project, UpdateProjectModel updateProjectModel) {
        if ( updateProjectModel == null ) {
            return;
        }

        if ( updateProjectModel.additionalNotes() != null ) {
            project.setAdditionalNotes( updateProjectModel.additionalNotes() );
        }
        if ( updateProjectModel.agreedToTerms() != null ) {
            project.setAgreedToTerms( updateProjectModel.agreedToTerms() );
        }
        if ( project.getApplicants() != null ) {
            List<String> list = updateProjectModel.applicants();
            if ( list != null ) {
                project.getApplicants().clear();
                project.getApplicants().addAll( list );
            }
        }
        else {
            List<String> list = updateProjectModel.applicants();
            if ( list != null ) {
                project.setApplicants( new ArrayList<String>( list ) );
            }
        }
        byte[] companyLogo = updateProjectModel.companyLogo();
        if ( companyLogo != null ) {
            project.setCompanyLogo( Arrays.copyOf( companyLogo, companyLogo.length ) );
        }
        if ( updateProjectModel.companyName() != null ) {
            project.setCompanyName( updateProjectModel.companyName() );
        }
        if ( updateProjectModel.completed() != null ) {
            project.setCompleted( updateProjectModel.completed() );
        }
        if ( updateProjectModel.contactInformation() != null ) {
            project.setContactInformation( updateProjectModel.contactInformation() );
        }
        if ( updateProjectModel.deadline() != null ) {
            project.setDeadline( updateProjectModel.deadline() );
        }
        if ( updateProjectModel.fileAccepted() != null ) {
            project.setFileAccepted( updateProjectModel.fileAccepted() );
        }
        if ( project.getFilter() != null ) {
            List<String> list1 = updateProjectModel.filter();
            if ( list1 != null ) {
                project.getFilter().clear();
                project.getFilter().addAll( list1 );
            }
        }
        else {
            List<String> list1 = updateProjectModel.filter();
            if ( list1 != null ) {
                project.setFilter( new ArrayList<String>( list1 ) );
            }
        }
        if ( updateProjectModel.id() != null ) {
            project.setId( updateProjectModel.id() );
        }
        if ( updateProjectModel.location() != null ) {
            project.setLocation( updateProjectModel.location() );
        }
        if ( updateProjectModel.posted() != null ) {
            project.setPosted( updateProjectModel.posted() );
        }
        if ( updateProjectModel.postedBy() != null ) {
            project.setPostedBy( updateProjectModel.postedBy() );
        }
        if ( updateProjectModel.projectBudget() != null ) {
            project.setProjectBudget( updateProjectModel.projectBudget() );
        }
        if ( updateProjectModel.projectCategory() != null ) {
            project.setProjectCategory( updateProjectModel.projectCategory() );
        }
        if ( updateProjectModel.projectDescription() != null ) {
            project.setProjectDescription( updateProjectModel.projectDescription() );
        }
        if ( updateProjectModel.projectDuration() != null ) {
            project.setProjectDuration( updateProjectModel.projectDuration() );
        }
        if ( updateProjectModel.projectTitle() != null ) {
            project.setProjectTitle( updateProjectModel.projectTitle() );
        }
        if ( project.getRequiredSkills() != null ) {
            List<String> list2 = updateProjectModel.requiredSkills();
            if ( list2 != null ) {
                project.getRequiredSkills().clear();
                project.getRequiredSkills().addAll( list2 );
            }
        }
        else {
            List<String> list2 = updateProjectModel.requiredSkills();
            if ( list2 != null ) {
                project.setRequiredSkills( new ArrayList<String>( list2 ) );
            }
        }
        if ( updateProjectModel.serviceProvider() != null ) {
            project.setServiceProvider( updateProjectModel.serviceProvider() );
        }
        if ( updateProjectModel.taken() != null ) {
            project.setTaken( updateProjectModel.taken() );
        }
    }
}
