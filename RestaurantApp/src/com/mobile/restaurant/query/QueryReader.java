package com.mobile.restaurant.query;

import com.mobile.restaurant.exception.ApplicationException;

public class QueryReader extends GenericFileReader{

	public QueryReader() throws ApplicationException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String readQuery() throws ApplicationException {
		return queryTemplate.replace("$type", "north indian");
	}

	public static void main(String[] args) throws ApplicationException {
		QueryReader obj = new QueryReader();
		String query = obj.readQuery();
		System.out.println(query);
	}
}
