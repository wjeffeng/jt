package com.jt.dubbo;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.jt.cart.entity.Cart;
import com.jt.common.vo.SysResult;


@Path("cart")
//支持的请求类型
@Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
//要求的返回值类型、
@Produces({ContentType.APPLICATION_JSON_UTF_8,ContentType.TEXT_XML_UTF_8})
public interface DubboCartService {
	@Path("query")
	SysResult myCart(@QueryParam(value="userId") Long userId);
	
	SysResult addCart(Cart cart);
	
	SysResult updateNum(Cart cart);
	
	SysResult deleteByWhere(Cart cart);
}
