package com.andyron.mapper;

import com.andyron.pojo.Provider;

import java.util.List;

public interface ProviderMapper {
    /**
     * 增加供应商
     */
    public int add(Provider provider);
    /**
     * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
     * @param provider
     */
    public List<Provider> getProviderList(Provider provider);
    /**
     * 通过proId删除Provider
     */
    public int deleteProviderById(Long id);
    /**
     * 通过proId获取Provider
     */
    public Provider getProviderById(Long id);
    /**
     * 修改用户信息
     */
    public int modify(Provider provider);
}
