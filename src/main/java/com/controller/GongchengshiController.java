
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 工程师
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/gongchengshi")
public class GongchengshiController {
    private static final Logger logger = LoggerFactory.getLogger(GongchengshiController.class);

    @Autowired
    private GongchengshiService gongchengshiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("工程师".equals(role))
            params.put("gongchengshiId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = gongchengshiService.queryPage(params);

        //字典表数据转换
        List<GongchengshiView> list =(List<GongchengshiView>)page.getList();
        for(GongchengshiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GongchengshiEntity gongchengshi = gongchengshiService.selectById(id);
        if(gongchengshi !=null){
            //entity转view
            GongchengshiView view = new GongchengshiView();
            BeanUtils.copyProperties( gongchengshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody GongchengshiEntity gongchengshi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,gongchengshi:{}",this.getClass().getName(),gongchengshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<GongchengshiEntity> queryWrapper = new EntityWrapper<GongchengshiEntity>()
            .eq("username", gongchengshi.getUsername())
            .or()
            .eq("gongchengshi_phone", gongchengshi.getGongchengshiPhone())
            .or()
            .eq("gongchengshi_id_number", gongchengshi.getGongchengshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GongchengshiEntity gongchengshiEntity = gongchengshiService.selectOne(queryWrapper);
        if(gongchengshiEntity==null){
            gongchengshi.setCreateTime(new Date());
            gongchengshi.setPassword("123456");
            gongchengshiService.insert(gongchengshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者工程师手机号或者工程师身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GongchengshiEntity gongchengshi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,gongchengshi:{}",this.getClass().getName(),gongchengshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<GongchengshiEntity> queryWrapper = new EntityWrapper<GongchengshiEntity>()
            .notIn("id",gongchengshi.getId())
            .andNew()
            .eq("username", gongchengshi.getUsername())
            .or()
            .eq("gongchengshi_phone", gongchengshi.getGongchengshiPhone())
            .or()
            .eq("gongchengshi_id_number", gongchengshi.getGongchengshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GongchengshiEntity gongchengshiEntity = gongchengshiService.selectOne(queryWrapper);
        if("".equals(gongchengshi.getGongchengshiPhoto()) || "null".equals(gongchengshi.getGongchengshiPhoto())){
                gongchengshi.setGongchengshiPhoto(null);
        }
        if(gongchengshiEntity==null){
            gongchengshiService.updateById(gongchengshi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者工程师手机号或者工程师身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        gongchengshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<GongchengshiEntity> gongchengshiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            GongchengshiEntity gongchengshiEntity = new GongchengshiEntity();
//                            gongchengshiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //gongchengshiEntity.setPassword("123456");//密码
//                            gongchengshiEntity.setGongchengshiName(data.get(0));                    //工程师姓名 要改的
//                            gongchengshiEntity.setGongchengshiPhone(data.get(0));                    //工程师手机号 要改的
//                            gongchengshiEntity.setGongchengshiIdNumber(data.get(0));                    //工程师身份证号 要改的
//                            gongchengshiEntity.setGongchengshiPhoto("");//详情和图片
//                            gongchengshiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            gongchengshiEntity.setGongchengshiEmail(data.get(0));                    //电子邮箱 要改的
//                            gongchengshiEntity.setCreateTime(date);//时间
                            gongchengshiList.add(gongchengshiEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //工程师手机号
                                if(seachFields.containsKey("gongchengshiPhone")){
                                    List<String> gongchengshiPhone = seachFields.get("gongchengshiPhone");
                                    gongchengshiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> gongchengshiPhone = new ArrayList<>();
                                    gongchengshiPhone.add(data.get(0));//要改的
                                    seachFields.put("gongchengshiPhone",gongchengshiPhone);
                                }
                                //工程师身份证号
                                if(seachFields.containsKey("gongchengshiIdNumber")){
                                    List<String> gongchengshiIdNumber = seachFields.get("gongchengshiIdNumber");
                                    gongchengshiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> gongchengshiIdNumber = new ArrayList<>();
                                    gongchengshiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("gongchengshiIdNumber",gongchengshiIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<GongchengshiEntity> gongchengshiEntities_username = gongchengshiService.selectList(new EntityWrapper<GongchengshiEntity>().in("username", seachFields.get("username")));
                        if(gongchengshiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GongchengshiEntity s:gongchengshiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //工程师手机号
                        List<GongchengshiEntity> gongchengshiEntities_gongchengshiPhone = gongchengshiService.selectList(new EntityWrapper<GongchengshiEntity>().in("gongchengshi_phone", seachFields.get("gongchengshiPhone")));
                        if(gongchengshiEntities_gongchengshiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GongchengshiEntity s:gongchengshiEntities_gongchengshiPhone){
                                repeatFields.add(s.getGongchengshiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [工程师手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //工程师身份证号
                        List<GongchengshiEntity> gongchengshiEntities_gongchengshiIdNumber = gongchengshiService.selectList(new EntityWrapper<GongchengshiEntity>().in("gongchengshi_id_number", seachFields.get("gongchengshiIdNumber")));
                        if(gongchengshiEntities_gongchengshiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GongchengshiEntity s:gongchengshiEntities_gongchengshiIdNumber){
                                repeatFields.add(s.getGongchengshiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [工程师身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        gongchengshiService.insertBatch(gongchengshiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        GongchengshiEntity gongchengshi = gongchengshiService.selectOne(new EntityWrapper<GongchengshiEntity>().eq("username", username));
        if(gongchengshi==null || !gongchengshi.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(gongchengshi.getId(),username, "gongchengshi", "工程师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","工程师");
        r.put("username",gongchengshi.getGongchengshiName());
        r.put("tableName","gongchengshi");
        r.put("userId",gongchengshi.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody GongchengshiEntity gongchengshi){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<GongchengshiEntity> queryWrapper = new EntityWrapper<GongchengshiEntity>()
            .eq("username", gongchengshi.getUsername())
            .or()
            .eq("gongchengshi_phone", gongchengshi.getGongchengshiPhone())
            .or()
            .eq("gongchengshi_id_number", gongchengshi.getGongchengshiIdNumber())
            ;
        GongchengshiEntity gongchengshiEntity = gongchengshiService.selectOne(queryWrapper);
        if(gongchengshiEntity != null)
            return R.error("账户或者工程师手机号或者工程师身份证号已经被使用");
        gongchengshi.setCreateTime(new Date());
        gongchengshiService.insert(gongchengshi);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        GongchengshiEntity gongchengshi = new GongchengshiEntity();
        gongchengshi.setPassword("123456");
        gongchengshi.setId(id);
        gongchengshiService.updateById(gongchengshi);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        GongchengshiEntity gongchengshi = gongchengshiService.selectOne(new EntityWrapper<GongchengshiEntity>().eq("username", username));
        if(gongchengshi!=null){
            gongchengshi.setPassword("123456");
            boolean b = gongchengshiService.updateById(gongchengshi);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrGongchengshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        GongchengshiEntity gongchengshi = gongchengshiService.selectById(id);
        if(gongchengshi !=null){
            //entity转view
            GongchengshiView view = new GongchengshiView();
            BeanUtils.copyProperties( gongchengshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }





}
