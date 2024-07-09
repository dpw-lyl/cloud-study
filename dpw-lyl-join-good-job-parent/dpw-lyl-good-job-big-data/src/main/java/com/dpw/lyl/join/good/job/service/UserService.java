/*
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    private static final String CACHE_KEY_PREFIX = "user:";
    private static final String LOCK_KEY_PREFIX = "lock:user:";

    public User getUser(Long id) {
        // 尝试获取分布式锁
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + id);
        lock.lock();

        try {
            // 尝试从缓存中获取数据
            User user = redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + id);
            if (user == null) {
                // 缓存中没有数据，从数据库获取并更新缓存
                user = userRepository.findById(id).orElse(null);
                if (user != null) {
                    redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + id, user, 30, TimeUnit.MINUTES);
                }
            }
            return user;
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public void updateUser(User user) {
        // 尝试获取分布式锁
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + user.getId());
        lock.lock();

        try {
            userRepository.save(user);
            // 更新后删除缓存，确保下次读取的是最新数据
            redisTemplate.delete(CACHE_KEY_PREFIX + user.getId());
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}*/
