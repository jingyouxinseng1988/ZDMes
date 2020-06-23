//package com.plc.platform.compo;
//
//import com.alibaba.fastjson.JSON;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Map;
//import java.util.Set;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//@Component("redisCompo")
//public class RedisCompo {
//
//    private static Logger logger = LoggerFactory.getLogger(RedisCompo.class);
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//
//
//    public void saveData(String key, Object obj) {
//        if (obj instanceof String) {
//            stringRedisTemplate.boundValueOps(key).set(obj.toString());
//        } else {
//            stringRedisTemplate.boundValueOps(key).set(JSON.toJSONString(obj));
//        }
//    }
//
//    public void save(String key, Object obj, int time, TimeUnit unit) {
//        if (obj instanceof String) {
//            stringRedisTemplate.boundValueOps(key).set(obj.toString(), time, unit);
//        } else {
//            stringRedisTemplate.boundValueOps(key).set(JSON.toJSONString(obj), time, unit);
//        }
//    }
//
//    public Object getKey(String key) {
//        if (key == null || key.length() == 0) {
//            return null;
//        }
//        Object data = stringRedisTemplate.boundValueOps(key).get();
//        return data;
//    }
//
//    public <T> String createToken(T t, int time, TimeUnit unit) {
//        String token = UUID.randomUUID().toString().replace("-", "");
//        stringRedisTemplate.boundValueOps(token).set(JSON.toJSONString(t), time, unit);
//        return token;
//    }
//
//    /**
//     * set 中添加元素
//     *
//     * @param key
//     * @param value
//     */
//    public void addSet(String key, String value) {
//        stringRedisTemplate.opsForSet().add(key, value);
//    }
//
//    /**
//     * set 移除元素
//     *
//     * @param key
//     * @param value
//     */
//    public void removeSet(String key, String value) {
//        stringRedisTemplate.opsForSet().remove(key, value);
//    }
//
//    public Set<String> getSet(String key) {
//        return stringRedisTemplate.opsForSet().members(key);
//    }
//
//
//    public boolean deleteKey(String key) {
//        return stringRedisTemplate.delete(key);
//    }
//
//    public void addHash(String key, Object hashKey, Object value) {
//        stringRedisTemplate.opsForHash().put(key, hashKey, value);
//    }
//
//    public Map<Object, Object> getHash(String key) {
//        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
//        return entries;
//    }
//
//    public Object getHash(String key, Object hashKey) {
//        return stringRedisTemplate.opsForHash().get(key, hashKey);
//    }
//
//    public void deleteHash(String key, Object hashKey) {
//        stringRedisTemplate.opsForHash().delete(key, hashKey);
//    }
//
//}
