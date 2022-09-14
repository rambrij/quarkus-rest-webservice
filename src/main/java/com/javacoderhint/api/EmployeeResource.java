package com.javacoderhint.api;

import com.javacoderhint.model.Employee;
import com.javacoderhint.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/employees")
public class EmployeeResource {
    @Inject
    EmployeeService employeeService;

    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response getEmployeeList(){
        return Response.ok(employeeService.findAll()).build();
    }

/*    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{ISBN}")
    public Book getBook(@PathParam("ISBN") Integer ISBN){
        return new Book("894343434", "Quarkus in Action", "Ram Brij");
    }*/

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response getEmployeeWithHeaders(@PathParam("id") String id)  {
        System.out.println("Going to update book" + id);
        Employee employee= employeeService.findEmployeeById(id);
        if (employee != null){
            return Response.status(Response.Status.OK)
                    .entity(employee)
                    .header("companyname", "javacoderhint")
                    .build();
        }else{
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return Response.created(UriBuilder.fromResource(EmployeeResource.class).build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id")  String id, Employee employeeRequest) {
        Employee employee= employeeService.findEmployeeById(employeeRequest.getId());
        if(employee == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else{
            employeeService.updateEmployee(employeeRequest);
            System.out.println("Employee Updated successfully");
            return Response.created(UriBuilder.fromResource(EmployeeResource.class).build()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id")  String id) {
        Employee employee= employeeService.findEmployeeById(id);
        if(employee == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else{
            employeeService.deleteEmployeeById(id);
            System.out.println("Employee delete successfully");
            return Response.status(Response.Status.OK).build();
        }
    }
}
