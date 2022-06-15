package com.outlierr.blog.api.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthType {
    None(1, "游客"),
    Local(2, "本地"),
    ;

    @EnumValue
    @JsonValue
    final Integer key;
    final String value;
}
