package com.attendace.dao.handlers;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;
import com.attendace.datasource.DbConnection;
import com.attendace.model.CourseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handler for processing user-course join requests in the chain of responsibility.
 * Handles operations for joining users to courses and retrieving joined data.
 */
public class DaoCourseUserJoin extends Handler {

    private static final String USERID = "id";
    private static final String COURSEID = "course_id";

    /**
     * Determines if this handler can process the given request.
     * If the request is not for USER_COURSE_JOIN, sets the next handler and returns false.
     *
     * @param request the request to check
     * @return true if this handler can process the request, false otherwise
     */
    @Override
    public boolean canProcess(Request request) {
        if (request.getDao() != RequestDao.USER_COURSE_JOIN) {
            setNextHandler(new DaoDegree());
            return false;
        } else {
            return true;
        }
    }

    /**
     * Processes the given request based on its type.
     * Supports GETDATA, SETDATA, and GETDATABYID.
     *
     * @param request the request to process
     * @return the result of processing the request, or false if not handled
     */
    @Override
    public Object process(Request request) {
        Map<String, Object> object = request.getData();

        if (request.getType() == RequestType.GETDATA) {
            return getData(object);
        } else if (request.getType() == RequestType.SETDATA) {
            setData(object);
            return true;
        } else if (request.getType() == RequestType.GETDATABYID) {
            return getDataById(object);
        }
        return false;
    }

    /**
     * Inserts a new user-course join entry into the COURSE_USER_JOIN table.
     *
     * @param data a map containing user and course IDs
     */
    public void setData(Map<String, Object> data) {
        int course_id = (int) data.get(COURSEID);
        int user_id = (int) data.get(USERID);

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO COURSE_USER_JOIN (user_id, course_id) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, user_id);
            ps.setInt(2, course_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of courses joined by a user, returning course details.
     *
     * @param object a map containing the user ID
     * @return a list of CourseModel objects, or an empty list if none found
     */
    public List<CourseModel> getDataById(Map<String, Object> object) {
        Integer userId = (Integer) object.get(USERID);
        ArrayList<CourseModel> data = new ArrayList<>();

        Connection connection = DbConnection.getConnection();
        String sql = "SELECT " +
                "course_name, " +
                "course_topic, " +
                "course_desc, " +
                "attendance_key, " +
                "min_attendance, " +
                "max_attendance, " +
                "course_active " +
                "FROM COURSE " +
                "JOIN COURSE_USER_JOIN ON COURSE_USER_JOIN.course_id = COURSE.id " +
                "WHERE COURSE_USER_JOIN.user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CourseModel course = new CourseModel(
                        rs.getString("course_name"),
                        rs.getString("course_topic"),
                        rs.getString("course_desc"),
                        rs.getInt("min_attendance"),
                        rs.getInt("max_attendance")
                );
                data.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (data.isEmpty()) {
            return new ArrayList<>();
        } else {
            return data;
        }
    }

    /**
     * Retrieves a list of course IDs joined by a user.
     *
     * @param object a map containing the user ID
     * @return a list of course IDs, or an empty list if none found
     */
    public List<Integer> getData(Map<String, Object> object) {
        Integer userId = (Integer) object.get(USERID);

        ArrayList<Integer> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT course_id FROM course_user_join WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            ps.setInt(1, userId);
            while (rs.next()) {
                data.add(Integer.valueOf(Integer.toString(rs.getInt(COURSEID))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (data.isEmpty()) {
            return new ArrayList<>();
        } else {
            return data;
        }
    }
}