package com.entity.model;

import com.entity.GongchengshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 工程师
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GongchengshiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 工程师姓名
     */
    private String gongchengshiName;


    /**
     * 工程师手机号
     */
    private String gongchengshiPhone;


    /**
     * 工程师身份证号
     */
    private String gongchengshiIdNumber;


    /**
     * 工程师头像
     */
    private String gongchengshiPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String gongchengshiEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：工程师姓名
	 */
    public String getGongchengshiName() {
        return gongchengshiName;
    }


    /**
	 * 设置：工程师姓名
	 */
    public void setGongchengshiName(String gongchengshiName) {
        this.gongchengshiName = gongchengshiName;
    }
    /**
	 * 获取：工程师手机号
	 */
    public String getGongchengshiPhone() {
        return gongchengshiPhone;
    }


    /**
	 * 设置：工程师手机号
	 */
    public void setGongchengshiPhone(String gongchengshiPhone) {
        this.gongchengshiPhone = gongchengshiPhone;
    }
    /**
	 * 获取：工程师身份证号
	 */
    public String getGongchengshiIdNumber() {
        return gongchengshiIdNumber;
    }


    /**
	 * 设置：工程师身份证号
	 */
    public void setGongchengshiIdNumber(String gongchengshiIdNumber) {
        this.gongchengshiIdNumber = gongchengshiIdNumber;
    }
    /**
	 * 获取：工程师头像
	 */
    public String getGongchengshiPhoto() {
        return gongchengshiPhoto;
    }


    /**
	 * 设置：工程师头像
	 */
    public void setGongchengshiPhoto(String gongchengshiPhoto) {
        this.gongchengshiPhoto = gongchengshiPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getGongchengshiEmail() {
        return gongchengshiEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setGongchengshiEmail(String gongchengshiEmail) {
        this.gongchengshiEmail = gongchengshiEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
