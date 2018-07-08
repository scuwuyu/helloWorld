package com.gongsi.mycoin.services.dubboImpl;

import com.gongsi.mycoin.dtos.*;
import com.gongsi.mycoin.apis.UserDubboService;
import com.gongsi.mycoin.utils.BeanMapper;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class UserDubboServiceImpl implements UserDubboService {


    public QueryUserResultDto findUser(String name, Integer age) {
        QueryUserDto dto = new QueryUserDto();
        dto.setName(name);
        dto.setAge(age);
        return null;
    }

    public QueryUserResultDto findUser(FindUserParamDto paramDto) {
        QueryUserDto dto = BeanMapper.map(paramDto, QueryUserDto.class);
        return null;
    }

    public Boolean updateUser(UpdateUserDto dto) {
        return null;
    }

    public Long saveUser(SaveUserDto dto) {

        return null;
    }

    public Boolean deleteUser(DeleteUserDto dto) {
        return null;
    }

}
