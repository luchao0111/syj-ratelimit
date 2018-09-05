package com.syj.config;


import com.syj.algorithm.CounterAlgorithm;
import com.syj.algorithm.LeakyBucketAlgorithm;
import com.syj.algorithm.RateLimiterAlgorithm;
import com.syj.algorithm.TokenBucketAlgorithm;
import com.syj.service.RateLimiter;
import com.syj.service.impl.MapRateLimiter;
import com.syj.util.Const;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * describe:
 *
 * @创建人 syj
 * @创建时间 2018/09/05
 * @描述
 */

@Configuration
public class ApplicationConfiguration {



    @Bean
    public AnnotationAspect annotationAspect(){
        return new AnnotationAspect();
    }

    @ConditionalOnProperty(prefix = Const.PREFIX, name = "db", havingValue = "redis")
    public static class RedisConfiguration {


    }

    @ConditionalOnProperty(prefix = Const.PREFIX, name = "db", havingValue = "jpa")
    public static class SpringDataConfiguration {

    }


    @ConditionalOnProperty(prefix = Const.PREFIX, name = "db", havingValue = "map", matchIfMissing = true)
    public static class MapConfiguration {
        @Bean
        public RateLimiter mapRateLimiter() {
            return new MapRateLimiter();
        }
    }
    @ConditionalOnProperty(prefix = Const.PREFIX, name = "algorithm", havingValue = "leaky")
    public static class LeakyBucketConfiguration {
        @Bean
        public RateLimiterAlgorithm rateLimiterAlgorithm() {
            return new LeakyBucketAlgorithm();
        }

    }

    @ConditionalOnProperty(prefix = Const.PREFIX, name = "algorithm", havingValue = "token")
    public static class TokenBucketConfiguration {
        @Bean
        public RateLimiterAlgorithm rateLimiterAlgorithm() {
            return new TokenBucketAlgorithm();
        }
    }


    @ConditionalOnProperty(prefix = Const.PREFIX, name = "algorithm", havingValue = "counter", matchIfMissing = true)
    public static class CounterConfiguration {
        @Bean
        public RateLimiterAlgorithm rateLimiterAlgorithm() {
            return new CounterAlgorithm();
        }
    }
}
