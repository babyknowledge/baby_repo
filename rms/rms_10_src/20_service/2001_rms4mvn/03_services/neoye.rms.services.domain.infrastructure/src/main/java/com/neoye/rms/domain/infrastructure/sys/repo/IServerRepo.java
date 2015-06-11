package com.neoye.rms.domain.infrastructure.sys.repo;

import java.util.List;

import com.neoye.rms.domain.infrastructure.sys.entity.ServerEntity;

public interface IServerRepo {

    int deleteServer(ServerEntity record);

    int insertServer(ServerEntity record);

    List<ServerEntity> selectServer(ServerEntity record);

    int updateServer(ServerEntity record);

}