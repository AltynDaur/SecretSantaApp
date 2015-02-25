package rest;

import dao.ChairDAO;
import dao.JPA;
import dao.LoginPersonDAO;
import dao.TeacherDAO;
import entity.Chair;
import entity.LoginPerson;
import entity.Person;
import entity.Teacher;
import security.EncryptByMD5;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@RequestScoped
@Path("/login")
public class LoginService {

    @Inject
    @JPA
    private LoginPersonDAO loginPersonDAO;

    @Inject
    @JPA
    private ChairDAO chairDAO;

    @Inject
    @JPA
    private TeacherDAO teacherDAO;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person find(LoginPerson loginPerson){
        LoginPerson loginPersonFromDB = loginPersonDAO.find(loginPerson.getLogin());

        if(loginPerson.getPassword().equals(loginPersonFromDB.getPassword())){
            return loginPersonFromDB.getPerson();
        }
        return null;
    }
}