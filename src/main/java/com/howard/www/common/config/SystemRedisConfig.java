package com.howard.www.common.config;

public class SystemRedisConfig {
	/**
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean(name="redisTemplate")
    public RedisTemplate<String, Object> initHowardRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new SystemRedisObjectSerializer());
        return template;
    }**/
}
