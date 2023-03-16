package com.example.finifocom_android_task;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataModal extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private String age;
    private String city;
    private String courseDuration;


    public DataModal(long id,String name,String age, String city) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public DataModal() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    // below line we are
    // creating getter and setters.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

/*public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }*/
}