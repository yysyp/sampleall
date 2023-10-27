package ps.demo.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.demo.common.MyMapperUtil;
import ps.demo.dto.MyMockDto;
import ps.demo.entity.MyMock;
import ps.demo.repository.MyMockMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MockerService {

    @Autowired
    private MyMockMapper myMockMapper;

    public List<MyMockDto>  findAll() {

        List<MyMockDto> all;

        List<MyMock> myMocks = myMockMapper.selectList(new QueryWrapper<>());
        all = MyMapperUtil.convert(myMocks, MyMockDto.class);

        return all;

    }

    public void save(MyMockDto myMockDto) {
        MyMock myMock = new MyMock();
        MyMapperUtil.convert(myMockDto, myMock);
        myMockMapper.insert(myMock);
    }

    public MyMock findByUri(String uri) {

        QueryWrapper<MyMock> queryWrapper = new QueryWrapper<MyMock>();
        queryWrapper.lambda().eq(MyMock::getUri, uri);
        return myMockMapper.selectOne(queryWrapper);

    }



}
