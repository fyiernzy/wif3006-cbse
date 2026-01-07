package assignment.wif3006cbse.features.profile.web;

import assignment.wif3006cbse.features.profile.application.dto.jobhistory.JobHistoryModel;
import assignment.wif3006cbse.features.profile.application.service.JobHistoryService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Component(service = {JobHistoryController.class}, property = {
    "osgi.jaxrs.resource=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/job-history")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JobHistoryController {

    @Reference
    private JobHistoryService jobHistoryService;

    // UC 03-04: View Job History - Get Job History by ID
    @GET
    @Path("/{id}")
    public Response getJobHistoryById(@PathParam("id") String id) {
        try {
            JobHistoryModel jobHistory = jobHistoryService.findJobHistoryById(id);
            return Response.ok(jobHistory).build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        }
    }

    // UC 03-04: View Job History - Get All Job History by User
    @GET
    @Path("/user/{userId}")
    public Response getJobHistoryByUserId(@PathParam("userId") String userId) {
        try {
            List<JobHistoryModel> jobHistories = jobHistoryService.findAllByUserId(userId);
            
            // UC 03-04 Exception Flow 2a: No job history
            if (jobHistories.isEmpty()) {
                return Response.ok()
                    .entity(Map.of("message", "No job history available.", "data",
                        jobHistories))
                    .build();
            }
            
            return Response.ok(jobHistories).build();
        } catch (Exception e) {
            // UC 03-04 Exception Flow 2b: Data retrieval error
            return Response.status(500)
                .entity(Map.of("error",
                    "Failed to retrieve job history. Please retry later."))
                .build();
        }
    }

    // Get Job History by Status (COMPLETED, ONGOING, CANCELLED)
    @GET
    @Path("/status/{status}")
    public Response getJobHistoryByStatus(@PathParam("status") String status) {
        try {
            List<JobHistoryModel> jobHistories = jobHistoryService.findAllByStatus(status);
            return Response.ok(jobHistories).build();
        } catch (Exception e) {
            return Response.status(500)
                .entity(Map.of("error", "Failed to retrieve job history: " + e.getMessage()))
                .build();
        }
    }
}
