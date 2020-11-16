import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.to.jing.course.sdk.domain.User;
import com.to.jing.course.server.AppServer;
import com.to.jing.course.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppServer.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    @Test
    public void one(){
        final String key = "redis_test";
        final String content = "这是一个redis测试";
        redisTemplate.opsForValue().set(key,content);
        Object result = redisTemplate.opsForValue().get(key);
        System.out.println(result);
    }

    /**
     * 测试对象
     */
    @Test
    public void user(){
        User user = userService.findUserById(1);
        final String key = "test_user";
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(user));
        User user1 = JSONObject.parseObject(stringRedisTemplate.opsForValue().get(key), User.class);
        System.out.println(user1);
    }
    /**
     * 测试集合
     */
    @Test
    public void testList(){
        List<User> list = new ArrayList<>();
        list.add(userService.findUserById(1));
        final String key = "test_list";
        stringRedisTemplate.opsForValue().set(key,JSON.toJSONString(list));
        System.out.println(JSONObject.parseArray(stringRedisTemplate.opsForValue().get(key),User.class));
    }

    /**
     * 测试list
     */
    @Test
    public void testRedisList(){
        final String key = "test_redis_list";
        ListOperations<String, Object> stringObjectListOperations = redisTemplate.opsForList();
        for (int i = 0; i < 2 ; i++){
            User user = new User(i + 1,"hah","aa",23,true);
            stringObjectListOperations.leftPush(key,user);
        }
        Object res = stringObjectListOperations.rightPop(key);
        User temp;
        while (res != null){
            temp = (User) res;
            System.out.println(temp);
            res = stringObjectListOperations.rightPop(key);
        }
    }
    /**
     * list pushALL
     */
    @Test
    public void testRedisPushAll(){
        final String key = "test_push_all";
        List<User> users = new ArrayList<>();
        for (int i = 0 ;i< 2;i++){
            users.add(new User(i + 1,"hah","aa",23,true));
        }
        redisTemplate.opsForList().leftPushAll(key,users);
        List<Object> range = redisTemplate.opsForList().range(key, 0, -1);
        System.out.println(range);
    }
}
