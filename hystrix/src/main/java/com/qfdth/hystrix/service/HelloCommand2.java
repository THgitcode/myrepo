package com.qfdth.hystrix.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

/**
 * @Author TH
 * @Date 2022/3/18 18:07
 * @Version jdk1.8
 */
public class HelloCommand2 extends HystrixCommand<String> {

    RestTemplate restTemplate;

    Integer id;

    public HelloCommand2(RestTemplate restTemplate,Integer id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("zhangsan")));
        this.restTemplate=restTemplate;
        this.id=id;
    }

    /**
     * 发起请求的地方
     *
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        //给个异常
        //int i=1/0;
        String s = restTemplate.getForObject("http://storage/deduct", String.class, id);
        System.out.println("s = " + s);
        return "aaaaa";
    }

    //返回缓存的Key
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    /**
     * 这是服务降级的方法，即 run 方法执行失败的时候，会自动触发该方法的执行
     *
     * @return
     */
    @Override
    protected String getFallback() {
        return "服务调用失败 error---fallback"+getExecutionException().getMessage();
    }
}
