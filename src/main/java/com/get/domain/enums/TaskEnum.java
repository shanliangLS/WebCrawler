package com.get.domain.enums;

public enum TaskEnum {
    // 爬虫task未提交
    TODO,
    // 爬虫task结束
    STOP,
    // 爬虫策略提交，运行出现错误
    CHECKING,
    // 爬虫task提交，正在运行
    RUNNING
}
