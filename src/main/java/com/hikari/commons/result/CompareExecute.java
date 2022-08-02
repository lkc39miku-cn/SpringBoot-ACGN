package com.hikari.commons.result;

import com.hikari.commons.key.NumberKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * ResultData
 *
 * @author lkc39miku_cn
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompareExecute<T> extends Result<T> {
    public enum ExecuteStatus {
        INSERT("添加成功", "添加失败"),
        UPDATE("修改成功", "修改失败"),
        DELETE("删除成功", "删除失败");

        private final String success;
        private final String error;

        ExecuteStatus(String success, String error) {
            this.success = success;
            this.error = error;
        }
    }

    public static Result<String> compare(int count, ExecuteStatus executeStatus) {
        if (count > NumberKey.ZERO) {
            return CompareExecute.success(executeStatus.success);
        } else {
            return CompareExecute.error(executeStatus.error);
        }
    }

    public static Result<String> compare(int count, int size, ExecuteStatus executeStatus) {
        if (count > NumberKey.ZERO && size > NumberKey.ZERO && count == size) {
            return CompareExecute.success(executeStatus.success);
        } else {
            return CompareExecute.error(executeStatus.error);
        }
    }

    public static Result<String> compare(boolean flag, ExecuteStatus executeStatus) {
        if (flag) {
            return CompareExecute.success(executeStatus.success);
        } else {
            return CompareExecute.error(executeStatus.error);
        }
    }

}
