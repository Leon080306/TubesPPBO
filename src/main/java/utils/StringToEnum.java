package utils;

import models.enums.Department;
import models.enums.MembershipLevel;
import models.enums.UserType;

public class StringToEnum {
    public static MembershipLevel toMembershipLevel(String membershipLevel) {
        return MembershipLevel.valueOf(membershipLevel.toUpperCase());
    }

    public static Department toDepartment(String department) {
        return Department.valueOf(department.toUpperCase());
    }

    public static UserType toUserType(String userType) {
        return UserType.valueOf(userType.toUpperCase());
    }
}
