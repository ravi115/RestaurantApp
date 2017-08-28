package com.mobile.restaurant.query;

import com.mobile.restaurant.constant.ApplicationConstant;
import com.mobile.restaurant.exception.ApplicationException;

public class QueryReader extends GenericFileReader{

	private String menuType;
	public QueryReader(final String menuType) throws ApplicationException {
		super();
		this.menuType = menuType;
	}

	/**
	 * 
	 */
	@Override
	public String readQuery() throws ApplicationException {
		return queryTemplate.replace(ApplicationConstant.PLACE_HOLDER, menuType);
	}

	public static void main(String[] args) throws ApplicationException {
		QueryReader obj = new QueryReader("");
		String query = obj.readQuery();
		System.out.println(query);
	}
}
