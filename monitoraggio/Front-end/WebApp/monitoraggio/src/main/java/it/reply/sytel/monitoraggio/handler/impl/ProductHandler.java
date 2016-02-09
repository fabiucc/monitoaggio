package it.reply.sytel.monitoraggio.handler.impl;

import java.util.List;

import it.reply.sytel.monitoraggio.beanVO.impl.Phone;
import it.reply.sytel.monitoraggio.dao.ProductDao;
import it.reply.sytel.monitoraggio.handler.ProductHandlerInterface;

public class ProductHandler implements ProductHandlerInterface{

	@Override
	public List<Phone> allphone() {
		List<Phone> phones=dao.allphone();
		
		return phones;
	}
	
	ProductDao dao=new ProductDao();

}
