package ClassroomApi.classroomApi.utility;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Group {
    GROUP_A("A"),
    GROUP_B("B"),
    GROUP_C("C"),
    GROUP_D("D");

    private final String group;


    Group(String group) {
        this.group = group;
    }

    @JsonValue
    public String getGroup() {
        return group;
    }
}

