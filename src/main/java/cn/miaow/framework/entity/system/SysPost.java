package cn.miaow.framework.entity.system;

import cn.miaow.framework.aspectj.annotation.Excel;
import cn.miaow.framework.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 岗位表 sys_post
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_post" )
public class SysPost extends BaseEntity {

    private static final long serialVersionUID = 7037141119219375475L;
    /**
     * 岗位序号
     */
    @Excel(name = "岗位序号" , cellType = Excel.ColumnType.NUMERIC)
    @TableId(type = IdType.ASSIGN_ID)
    private Long postId;

    /**
     * 岗位编码
     */
    @Excel(name = "岗位编码" )
    @NotBlank(message = "岗位编码不能为空" )
    @Size(max = 64, message = "岗位编码长度不能超过64个字符" )
    private String postCode;

    /**
     * 岗位名称
     */
    @Excel(name = "岗位名称" )
    @NotBlank(message = "岗位名称不能为空" )
    @Size(max = 50, message = "岗位名称长度不能超过50个字符" )
    private String postName;

    /**
     * 岗位排序
     */
    @Excel(name = "岗位排序" )
    @NotNull(message = "显示顺序不能为空" )
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态" , readConverterExp = "0=正常,1=停用" )
    private String status;

    /**
     * 用户是否存在此岗位标识 默认不存在
     */
    private boolean flag = false;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("postId" , getPostId())
                .append("postCode" , getPostCode())
                .append("postName" , getPostName())
                .append("postSort" , getPostSort())
                .append("status" , getStatus())
                .append("createBy" , getCreateBy())
                .append("createTime" , getCreateTime())
                .append("updateBy" , getUpdateBy())
                .append("updateTime" , getUpdateTime())
                .append("remark" , getRemark())
                .toString();
    }
}
