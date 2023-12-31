package cn.miaow.framework.controller.system;

import cn.miaow.framework.config.MiaowConfig;
import cn.miaow.framework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author miaow
 */
@RestController
public class SysIndexController {
    /**
     * 系统基础配置
     */
    private final MiaowConfig miaowConfig;

    public SysIndexController(MiaowConfig miaowConfig) {
        this.miaowConfig = miaowConfig;
    }

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/" )
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。" , miaowConfig.getName(), miaowConfig.getVersion());
    }
}
