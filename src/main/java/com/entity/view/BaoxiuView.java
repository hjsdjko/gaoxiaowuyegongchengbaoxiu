package com.entity.view;

import com.entity.BaoxiuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 报修
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("baoxiu")
public class BaoxiuView extends BaoxiuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 报修类型的值
		*/
		private String baoxiuValue;
		/**
		* 报修状态的值
		*/
		private String baoxiuZhuangtaiValue;



		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;

		//级联表 gongchengshi
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
			* 电子邮箱
			*/
			private String gongchengshiEmail;

	public BaoxiuView() {

	}

	public BaoxiuView(BaoxiuEntity baoxiuEntity) {
		try {
			BeanUtils.copyProperties(this, baoxiuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 报修类型的值
			*/
			public String getBaoxiuValue() {
				return baoxiuValue;
			}
			/**
			* 设置： 报修类型的值
			*/
			public void setBaoxiuValue(String baoxiuValue) {
				this.baoxiuValue = baoxiuValue;
			}
			/**
			* 获取： 报修状态的值
			*/
			public String getBaoxiuZhuangtaiValue() {
				return baoxiuZhuangtaiValue;
			}
			/**
			* 设置： 报修状态的值
			*/
			public void setBaoxiuZhuangtaiValue(String baoxiuZhuangtaiValue) {
				this.baoxiuZhuangtaiValue = baoxiuZhuangtaiValue;
			}













				//级联表的get和set yonghu

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}




				//级联表的get和set gongchengshi

					/**
					* 获取： 工程师姓名
					*/
					public String getGongchengshiName() {
						return gongchengshiName;
					}
					/**
					* 设置： 工程师姓名
					*/
					public void setGongchengshiName(String gongchengshiName) {
						this.gongchengshiName = gongchengshiName;
					}

					/**
					* 获取： 工程师手机号
					*/
					public String getGongchengshiPhone() {
						return gongchengshiPhone;
					}
					/**
					* 设置： 工程师手机号
					*/
					public void setGongchengshiPhone(String gongchengshiPhone) {
						this.gongchengshiPhone = gongchengshiPhone;
					}

					/**
					* 获取： 工程师身份证号
					*/
					public String getGongchengshiIdNumber() {
						return gongchengshiIdNumber;
					}
					/**
					* 设置： 工程师身份证号
					*/
					public void setGongchengshiIdNumber(String gongchengshiIdNumber) {
						this.gongchengshiIdNumber = gongchengshiIdNumber;
					}

					/**
					* 获取： 工程师头像
					*/
					public String getGongchengshiPhoto() {
						return gongchengshiPhoto;
					}
					/**
					* 设置： 工程师头像
					*/
					public void setGongchengshiPhoto(String gongchengshiPhoto) {
						this.gongchengshiPhoto = gongchengshiPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getGongchengshiEmail() {
						return gongchengshiEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setGongchengshiEmail(String gongchengshiEmail) {
						this.gongchengshiEmail = gongchengshiEmail;
					}



}
