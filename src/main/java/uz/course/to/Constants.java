package uz.course.to;


import java.io.Serializable;

public interface Constants extends Serializable {
    String ACCESS_TOKEN = "access_token";

    interface Cache {
        String QUERY_USER = "query.user";
        String QUERY_TEACHERS = "query.teachers";
        String QUERY_LESSONS = "query.lessons";
        String QUERY_BRANCH = "query.branches";
        String QUERY_REFERENCE = "query.reference";
        String QUERY_GROUP = "query.group";
        String QUERY_PERIOD = "query.period";
        String QUERY_ROLE = "query.role";
    }

}