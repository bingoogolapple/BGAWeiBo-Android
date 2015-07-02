package cn.bingoogolapple.volley;

import android.app.Activity;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/2 10:14
 * 描述:
 */
public abstract class JsonRespDelegate<T> extends VolleyRespDelegate<T> {
    protected static Gson sGson = new Gson();

    protected JsonRespDelegate(Activity activity) {
        super(activity);
    }

    protected Class<T> getTClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type resultType = type.getActualTypeArguments()[0];
        // ImplForType, ParameterizedTypeImpl
//        if ("ParameterizedTypeImpl".equals(resultType.getClass().getSimpleName()) || "ImplForType".equals(resultType.getClass().getSimpleName())) {
//            try {
//                Field field = resultType.getClass().getDeclaredField("rawTypeName");
//                field.setAccessible(true);
//                String rawTypeName = (String) field.get(resultType);
//                return (Class<T>) Class.forName(rawTypeName);
//            } catch (Exception e) {
//                return (Class<T>) Collection.class;
//            }
//        } else {
//            return (Class<T>) resultType;
//        }

        if (resultType instanceof Class) {
            return (Class<T>) resultType;
        } else {
            // 处理集合
            try {
                Field field = resultType.getClass().getDeclaredField("rawTypeName");
                field.setAccessible(true);
                String rawTypeName = (String) field.get(resultType);
                return (Class<T>) Class.forName(rawTypeName);
            } catch (Exception e) {
                return (Class<T>) Collection.class;
            }
        }
    }

    protected abstract void handleResponse(String response);

    protected abstract void onJsonError(Exception e);

}