package assignment.wif3006cbse.features.project.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project entity representing a job/project posting by a recruiter.
 * Corresponds to the Project model in the dependency table (Depth 0).
 */
public class Project implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String postedBy; // User ID of the recruiter who posted
    private String companyName;
    private String projectTitle;
    private String projectDescription;
    private String location;
    private String projectCategory;
    private String projectDuration; // "Short Term" or "Long Term"
    private List<String> filters; // Combined filters for search
    private List<String> requiredSkills;
    private BigDecimal projectBudget;
    private LocalDate deadline;
    private String contactInformation;
    private String additionalNotes;
    private boolean agreedToTerms;

    // Status flags
    private boolean posted;
    private boolean taken;
    private boolean completed;
    private boolean fileAccepted;

    // Service provider
    private String serviceProvider; // User ID of the freelancer who took the project

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Attachments
    private List<String> uploadedFileIds;

    public Project() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.requiredSkills = new ArrayList<>();
        this.filters = new ArrayList<>();
        this.posted = true;
        this.taken = false;
        this.completed = false;
        this.fileAccepted = false;
        this.uploadedFileIds = new ArrayList<>();
    }

    public Project(String postedBy, String projectTitle, String projectDescription,
            String location, String projectCategory, String projectDuration,
            List<String> requiredSkills, BigDecimal projectBudget, LocalDate deadline,
            String contactInformation, String additionalNotes, boolean agreedToTerms) {
        this();
        this.postedBy = postedBy;
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.location = location;
        this.projectCategory = projectCategory;
        this.projectDuration = projectDuration;
        this.requiredSkills = requiredSkills != null ? new ArrayList<>(requiredSkills) : new ArrayList<>();
        this.projectBudget = projectBudget;
        this.deadline = deadline;
        this.contactInformation = contactInformation;
        this.additionalNotes = additionalNotes;
        this.agreedToTerms = agreedToTerms;
        // Auto-generate filters
        this.filters = generateFilters();
        this.uploadedFileIds = new ArrayList<>();
    }

    private List<String> generateFilters() {
        List<String> generatedFilters = new ArrayList<>();
        if (projectCategory != null)
            generatedFilters.add(projectCategory);
        if (projectDuration != null)
            generatedFilters.add(projectDuration);
        if (location != null)
            generatedFilters.add(location);
        return generatedFilters;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters != null ? new ArrayList<>(filters) : new ArrayList<>();
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills != null ? new ArrayList<>(requiredSkills) : new ArrayList<>();
    }

    public BigDecimal getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(BigDecimal projectBudget) {
        this.projectBudget = projectBudget;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public boolean isAgreedToTerms() {
        return agreedToTerms;
    }

    public void setAgreedToTerms(boolean agreedToTerms) {
        this.agreedToTerms = agreedToTerms;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isFileAccepted() {
        return fileAccepted;
    }

    public void setFileAccepted(boolean fileAccepted) {
        this.fileAccepted = fileAccepted;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    public void refreshFilters() {
        this.filters = generateFilters();
    }

    public List<String> getUploadedFileIds() {
        return uploadedFileIds;
    }

    public void setUploadedFileIds(List<String> uploadedFileIds) {
        this.uploadedFileIds = uploadedFileIds != null ? new ArrayList<>(uploadedFileIds) : new ArrayList<>();
    }
}
