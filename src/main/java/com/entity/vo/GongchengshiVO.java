package com.entity.vo;

import com.entity.GongchengshiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 工程师
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("gongchengshi")
public class GongchengshiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 工程师姓名
     */

    @TableField(value = "gongchengshi_name")
    private String gongchengshiName;


    /**
     * 工程师手机号
     */

    @TableField(value = "gongchengshi_phone")
    private String gongchengshiPhone;


    /**
     * 工程师身份证号
     */

    @TableField(value = "gongchengshi_id_number")
    private String gongchengshiIdNumber;


    /**
     * 工程师头像
     */

    @TableField(value = "gongchengshi_photo")
    private String gongchengshiPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 电子邮箱
     */

    @TableField(value = "gongchengshi_email")
    private String gongchengshiEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：工程师姓名
	 */
    public String getGongchengshiName() {
        return gongchengshiName;
    }


    /**
	 * 获取：工程师姓名
	 */

    public void setGongchengshiName(String gongchengshiName) {
        this.gongchengshiName = gongchengshiName;
    }
    /**
	 * 设置：工程师手机号
	 */
    public String getGongchengshiPhone() {
        return gongchengshiPhone;
    }


    /**
	 * 获取：工程师手机号
	 */

    public void setGongchengshiPhone(String gongchengshiPhone) {
        this.gongchengshiPhone = gongchengshiPhone;
    }
    /**
	 * 设置：工程师身份证号
	 */
    public String getGongchengshiIdNumber() {
        return gongchengshiIdNumber;
    }


    /**
	 * 获取：工程师身份证号
	 */

    public void setGongchengshiIdNumber(String gongchengshiIdNumber) {
        this.gongchengshiIdNumber = gongchengshiIdNumber;
    }
    /**
	 * 设置：工程师头像
	 */
    public String getGongchengshiPhoto() {
        return gongchengshiPhoto;
    }


    /**
	 * 获取：工程师头像
	 */

    public void setGongchengshiPhoto(String gongchengshiPhoto) {
        this.gongchengshiPhoto = gongchengshiPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getGongchengshiEmail() {
        return gongchengshiEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setGongchengshiEmail(String gongchengshiEmail) {
        this.gongchengshiEmail = gongchengshiEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
