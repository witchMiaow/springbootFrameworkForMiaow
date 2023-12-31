package cn.miaow.framework.controller.system;

import cn.miaow.framework.aspectj.annotation.Log;
import cn.miaow.framework.constant.enums.BusinessType;
import cn.miaow.framework.entity.system.SysConfig;
import cn.miaow.framework.model.AjaxResult;
import cn.miaow.framework.model.BaseController;
import cn.miaow.framework.model.TableDataInfo;
import cn.miaow.framework.service.system.ISysConfigService;
import cn.miaow.framework.util.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author miaow
 */
@RestController
@RequestMapping("/system/config" )
public class SysConfigController extends BaseController {
    private final ISysConfigService configService;

    public SysConfigController(ISysConfigService configService) {
        this.configService = configService;
    }

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("@ss.hasPermission('system:config:list')" )
    @GetMapping("/list" )
    public TableDataInfo list(SysConfig config) {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    @Log(title = "参数管理" , businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermission('system:config:export')" )
    @PostMapping("/export" )
    public void export(HttpServletResponse response, SysConfig config) {
        List<SysConfig> list = configService.selectConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<>(SysConfig.class);
        util.exportExcel(response, list, "参数数据" );
    }

    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermission('system:config:query')" )
    @GetMapping(value = "/{configId}" )
    public AjaxResult getInfo(@PathVariable Long configId) {
        return success(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}" )
    public AjaxResult getConfigKey(@PathVariable String configKey) {
        return success(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("@ss.hasPermission('system:config:add')" )
    @Log(title = "参数管理" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysConfig config) {
        if (configService.checkConfigKeyNotUnique(config)) {
            return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在" );
        }
        config.setCreateBy(getUsername());
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPermission('system:config:edit')" )
    @Log(title = "参数管理" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysConfig config) {
        if (configService.checkConfigKeyNotUnique(config)) {
            return error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在" );
        }
        config.setUpdateBy(getUsername());
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ss.hasPermission('system:config:remove')" )
    @Log(title = "参数管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}" )
    public AjaxResult remove(@PathVariable Long[] configIds) {
        configService.deleteConfigByIds(configIds);
        return success();
    }

    /**
     * 刷新参数缓存
     */
    @PreAuthorize("@ss.hasPermission('system:config:remove')" )
    @Log(title = "参数管理" , businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache" )
    public AjaxResult refreshCache() {
        configService.resetConfigCache();
        return success();
    }
}
