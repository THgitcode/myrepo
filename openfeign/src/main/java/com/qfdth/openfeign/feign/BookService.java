package com.qfdth.openfeign.feign;

import com.qfdth.service.api.IBookController;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author TH
 * @Date 2022/3/18 12:54
 * @Version jdk1.8
 */
//表示这个 Feign 客户端绑定了 storage 服务
@FeignClient("storage")
public interface BookService extends IBookController {
}
