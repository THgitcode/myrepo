package com.qfdth.business;

import com.qfdth.demo.service.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author TH  
 * @Date 2022/3/17 22:34
 * @Version jdk1.8  
*  hello123！！！
 */
@RestController
public class HelloController {
    //注意这里使用 Spring Cloud 中的接口
    //通过 DiscoveryClient 可以在 Eureka 上查询不同服务的信息
    //discovery 发现
    @Autowired
    DiscoveryClient  discoveryClient;
    //atomic  原子的 确保安全  轮训方式 负载均衡
    AtomicInteger count=new AtomicInteger(1);

    /**
     * 这是一个用户下单的接口
     */
    @GetMapping("/hello")
    public void hello() throws IOException {
        //根据服务名查询服务信息，由于 storage 可能是集群化部署，所以返回的是一个 List 集合
        List<ServiceInstance> list = discoveryClient.getInstances("storage");
        //获取一个实例对象   count.getAndAdd（）拿到具体数字  1 每次获取旧值加1 和list.size()取余确保每次拿的不一样
        ServiceInstance instance = list.get(count.getAndAdd(1)%list.size());
        //获取 storage 服务的主机地址
        String host = instance.getHost();
        //获取 storage 服务的端口号
        int port = instance.getPort();
        URL url = new URL("http://"+host+":"+port+"/deduct");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        //建立连接
        con.connect();
        if (con.getResponseCode() == 200) {
            //说明请求成功  stream  InputStreamReader转换流 把字节转换为字符
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String s = br.readLine();
            System.out.println("s = " + s);
            br.close();
        }
    }

    @Autowired
    RestTemplate restTemplate;
    //无负载均衡
    @GetMapping("/hello02")
    public void hello2() {
        //根据服务名查询服务信息，由于 storage 可能是集群化部署，所以返回的是一个 List 集合
        List<ServiceInstance> list = discoveryClient.getInstances("storage");
        //获取一个实例对象
        ServiceInstance instance = list.get(count.getAndAdd(1) % list.size());
        //获取 storage 服务的主机地址
        String host = instance.getHost();
        //获取 storage 服务的端口号
        int port = instance.getPort();
        String url = "http://"+host+":"+port+"/deduct";
        String s=restTemplate.getForObject(url,String.class);
        System.out.println("s = " + s);
    }

    //有负载均衡
    @GetMapping("/hello03")
    public void hello03(){
        //带有负载均衡功能的 restTemplate，会自带一个拦截器，这个拦截器会拦截下请求的 URL 地址，
        // 然后从中提取出 storage 字符串，提取出来之后，底层就是通过 DiscoveryClient 去查找该服务的信息，
        // 然后获取到服务的地址和端口，再根据地址和端口拼接出来一个完整的请求地址
        String s = restTemplate.getForObject("http://storage/deduct", String.class);
        System.out.println("s = " + s);
    }

    //注意开启了负载均衡 增删改查  getForEntity可以得到响应头信息
    @GetMapping("/hello4")
    public void hello4() {
        //两种传参数方式 1
        ResponseEntity<Book> entity01 = restTemplate.getForEntity("http://storage/book/?id={1}", Book.class, 99);
        System.out.println("entity01.getBody() = " + entity01.getBody());
        //2
        Map<String, Object> params = new HashMap<>();
        params.put("id", 99);
        ResponseEntity<Book> entity02 = restTemplate.getForEntity("http://storage/book/?id={id}", Book.class, params);
        //获取接口返回的对象
        Book book = entity02.getBody();
        System.out.println("book = " + book);
        //获取 HTTP 响应状态码
        int statusCodeValue = entity02.getStatusCodeValue();
        System.out.println("statusCodeValue = " + statusCodeValue);
        HttpStatus statusCode = entity02.getStatusCode();
        // 200 OK
        System.out.println("statusCode = " + statusCode);
        //获取 HTTP 响应头
        HttpHeaders headers = entity02.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String key : keySet) {
            List<String> v = headers.get(key);
            System.out.println(key + "----->" + v);
        }

        System.out.println("===============================POST============================");
        //注意，这种参数传递形式是 key-value 形式，如果直接创建了一个 book 对象，
        // 则参数的传递形式是 JSON 形式，即对方的接口需要有 @RequestBody 注解
        MultiValueMap<String,Object> p = new LinkedMultiValueMap<>();
        p.add("id", 99);
        p.add("name", "三国演义");
        p.add("author","罗贯中");
        Book b1 = restTemplate.postForObject("http://storage/book/", p, Book.class);
        System.out.println("b1 = " + b1);
        System.out.println("===============================POST--forLocation============================");
        //post 一般用来做添加，添加完成后，一般要重定向到列表页面，所以这个接口的返回值是一个 URI，这是一个重定向的地址
        //uri 就是要重定向的地址
        //调用这个方法有一个要求：目标i接口必须返回重定向，必须是响应 302
        URI uri = restTemplate.postForLocation("http://storage/book/add",b1);
        System.out.println("uri = " + uri);
        System.out.println("===============================PUT============================");
        restTemplate.put("http://storage/book/", b1);
        System.out.println("===============================DELETE============================");
        restTemplate.delete("http://storage/book/{1}", 99);
    }
}
