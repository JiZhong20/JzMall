package jz.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/25
 * @return
 */
public class WebUtils {
        public static <T> T copyParamToBean(Map value, T bean){
            System.out.println("注入之前" + bean);
            try {
                BeanUtils.populate(bean,value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println("注入之后"+bean);
            return bean;
        }

    public static int parseInt(String strint,int defaultValue){
            try {
                return Integer.parseInt(strint);
            }catch (Exception e){
                //e.printStackTrace();
            }
            return defaultValue;
        }

}
