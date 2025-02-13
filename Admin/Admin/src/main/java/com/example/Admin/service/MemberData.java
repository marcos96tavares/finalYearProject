package com.example.Admin.service;

import java.util.List;
import java.util.Map;

public interface MemberData {

    int totalOfMembers();
    int totalActiveClases();
    double attendanceRate();

    List<String> classPopularityRanking();

    Map<String , Integer> classAttendance();
}
