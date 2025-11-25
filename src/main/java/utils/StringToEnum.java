package utils;

import models.enums.Department;
import models.enums.MembershipLevel;

public class StringToEnum {
    public static MembershipLevel toMembershipLevel(String membershipLevel) {
        return MembershipLevel.valueOf(membershipLevel.toUpperCase());
    }

    public static Department toDepartment(String department) {
        return Department.valueOf(department.toUpperCase());
    }
}
