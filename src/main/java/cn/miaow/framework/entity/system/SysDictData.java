package cn.miaow.framework.entity.system;

import cn.miaow.framework.aspectj.annotation.Excel;
import cn.miaow.framework.constant.UserConstants;
import cn.miaow.framework.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 字典数据表 sys_dict_data
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_dict_data" )
public class SysDictData extends BaseEntity {
    private static final long serialVersionUID = -4865495495318862904L;
    /**
     * 字典编码
     */
    @Excel(name = "字典编码" , cellType = Excel.ColumnType.NUMERIC)
    @TableId(type = IdType.ASSIGN_ID)
    private Long dictCode;

    /**
     * 字典排序
     */
    @Excel(name = "字典排序" , cellType = Excel.ColumnType.NUMERIC)
    private Long dictSort;

    /**
     * 字典标签
     */
    @Excel(name = "字典标签" )
    @NotBlank(message = "字典标签不能为空" )
    @Size(max = 100, message = "字典标签长度不能超过100个字符" )
    private String dictLabel;

    /**
     * 字典键值
     */
    @Excel(name = "字典键值" )
    @NotBlank(message = "字典键值不能为空" )
    @Size(max = 100, message = "字典键值长度不能超过100个字符" )
    private String dictValue;

    /**
     * 字典类型
     */
    @Excel(name = "字典类型" )
    @NotBlank(message = "字典类型不能为空" )
    @Size(max = 100, message = "字典类型长度不能超过100个字符" )
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     */
    @Size(max = 100, message = "样式属性长度不能超过100个字符" )
    private String cssClass;

    /**
     * 表格字典样式
     */
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     */
    @Excel(name = "是否默认" , readConverterExp = "Y=是,N=否" )
    private String isDefault;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态" , readConverterExp = "0=正常,1=停用" )
    private String status;


    public boolean getDefault() {
        return UserConstants.YES.equals(this.isDefault);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("dictCode" , getDictCode())
                .append("dictSort" , getDictSort())
                .append("dictLabel" , getDictLabel())
                .append("dictValue" , getDictValue())
                .append("dictType" , getDictType())
                .append("cssClass" , getCssClass())
                .append("listClass" , getListClass())
                .append("isDefault" , getIsDefault())
                .append("status" , getStatus())
                .append("createBy" , getCreateBy())
                .append("createTime" , getCreateTime())
                .append("updateBy" , getUpdateBy())
                .append("updateTime" , getUpdateTime())
                .append("remark" , getRemark())
                .toString();
    }
}
