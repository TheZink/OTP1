package com.attendace.Controller;

import com.attendace.Model.AttendanceModel;
import com.attendace.dao.Request;
import com.attendace.dao.handlers.DefaultHandler;
import com.attendace.dao.requests.RequestDao;
import com.attendace.dao.requests.RequestType;

import java.util.HashMap;
import java.util.Map;

public class AttendanceController {

    private AttendanceModel attendanceModel;
    private DefaultHandler handler;
    private Request request;
    private Map<String, Object> data;

    //ADD FXML TAGS HERE. WILL BE USED FOR FUNCTIONS INSTEAD OF STRINGS.

    public AttendanceController() {
        this.handler = new DefaultHandler();
    }

    //ActionEvent event parameter later, strings used for testing only
    public void addAttendance(int courseId, int userId, int staffId, boolean attendanceStatus, int currentAttendance) {
        data = new HashMap<>();
        data.put("course_id", courseId);
        data.put("user_id", userId);
        data.put("staff_id", staffId);
        data.put("atten_status", attendanceStatus);
        data.put("atten_current", currentAttendance);

        request = new Request(RequestDao.ATTENDANCE, RequestType.SETDATA, data);
        boolean addedAttendance = (boolean)handler.handle(request);

        if(addedAttendance) {
            System.out.println("Added attendance for user"+ userId);
        } else {
            System.out.println("Failed to add attendance");
        }
    }
    public AttendanceModel getAttendance() {
        return attendanceModel;
    }
}
