package com.zwc.springbootstudy.chk;

/**
 * @author: zhangwch
 * @create: 2020-09-18 09:30
 **/
public class ReqChkParam extends BaseDO {

    @ChkParam(name = "序号", type = "", dac = "")
    private String id;

    @ChkParam(name = "姓名", type = "", dac = "")
    private String name;

    @ChkParam(name = "年龄", type = "", dac = "")
    private String age;

    public ReqChkParam() {
    }

    public ReqChkParam(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ReqChkParam{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
