package top.arhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arhi.mapper.LogMapper;
import top.arhi.service.LogService;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void logInfo(String outName, String inName, Integer money, String dateStr, Boolean flag) {
        logMapper.logInfo(outName,inName,money,dateStr,flag);
    }
}
